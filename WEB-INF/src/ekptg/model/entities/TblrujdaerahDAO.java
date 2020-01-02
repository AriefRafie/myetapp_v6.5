package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujdaerah entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujdaerah
 * @author MyEclipse Persistence Tools
 */

public class TblrujdaerahDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujdaerahDAO.class);
	// property constants
	public static final String KOD_DAERAH = "kodDaerah";
	public static final String KOD_DAERAH_PTG = "kodDaerahPTG";
	public static final String NAMA_DAERAH = "namaDaerah";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujdaerah transientInstance) {
		log.debug("saving Tblrujdaerah instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujdaerah persistentInstance) {
		log.debug("deleting Tblrujdaerah instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujdaerah findById(java.lang.Long id) {
		log.debug("getting Tblrujdaerah instance with id: " + id);
		try {
			Tblrujdaerah instance = (Tblrujdaerah) getSession().get(
					"ekptg.model.entities.Tblrujdaerah", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujdaerah instance) {
		log.debug("finding Tblrujdaerah instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujdaerah").add(
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
		log.debug("finding Tblrujdaerah instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujdaerah as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodDaerah(Object kodDaerah) {
		return findByProperty(KOD_DAERAH, kodDaerah);
	}

	public List findByKodDaerahPTG(Object kodDaerahPTG) {
		return findByProperty(KOD_DAERAH_PTG, kodDaerahPTG);
	}
	
	public List findByNamaDaerah(Object namaDaerah) {
		return findByProperty(NAMA_DAERAH, namaDaerah);
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
		log.debug("finding all Tblrujdaerah instances");
		try {
			String queryString = "from Tblrujdaerah";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujdaerah merge(Tblrujdaerah detachedInstance) {
		log.debug("merging Tblrujdaerah instance");
		try {
			Tblrujdaerah result = (Tblrujdaerah) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujdaerah instance) {
		log.debug("attaching dirty Tblrujdaerah instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujdaerah instance) {
		log.debug("attaching clean Tblrujdaerah instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}