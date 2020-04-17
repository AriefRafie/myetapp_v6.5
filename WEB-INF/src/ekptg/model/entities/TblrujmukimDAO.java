package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujmukim entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujmukim
 * @author MyEclipse Persistence Tools
 */

public class TblrujmukimDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujmukimDAO.class);
	// property constants
	public static final String KOD_MUKIM = "kodMukim";
	public static final String NAMA_MUKIM = "namaMukim";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujmukim transientInstance) {
		log.debug("saving Tblrujmukim instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujmukim persistentInstance) {
		log.debug("deleting Tblrujmukim instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujmukim findById(java.lang.Long id) {
		log.debug("getting Tblrujmukim instance with id: " + id);
		try {
			Tblrujmukim instance = (Tblrujmukim) getSession().get(
					"ekptg.model.entities.Tblrujmukim", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujmukim instance) {
		log.debug("finding Tblrujmukim instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujmukim").add(
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
		log.debug("finding Tblrujmukim instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblrujmukim as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodMukim(Object kodMukim) {
		return findByProperty(KOD_MUKIM, kodMukim);
	}

	public List findByNamaMukim(Object namaMukim) {
		return findByProperty(NAMA_MUKIM, namaMukim);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujmukim instances");
		try {
			String queryString = "from Tblrujmukim";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujmukim merge(Tblrujmukim detachedInstance) {
		log.debug("merging Tblrujmukim instance");
		try {
			Tblrujmukim result = (Tblrujmukim) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujmukim instance) {
		log.debug("attaching dirty Tblrujmukim instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujmukim instance) {
		log.debug("attaching clean Tblrujmukim instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}