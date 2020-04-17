package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangk entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangk
 * @author MyEclipse Persistence Tools
 */

public class TblpptborangkDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptborangkDAO.class);
	// property constants
	public static final String FLAG_SEGERA = "flagSegera";
	public static final String ID_BORANGI = "idBorangi";
	public static final String ID_BORANGG = "idBorangg";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangk transientInstance) {
		log.debug("saving Tblpptborangk instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangk persistentInstance) {
		log.debug("deleting Tblpptborangk instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangk findById(java.lang.Long id) {
		log.debug("getting Tblpptborangk instance with id: " + id);
		try {
			Tblpptborangk instance = (Tblpptborangk) getSession().get(
					"ekptg.model.entities.Tblpptborangk", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangk instance) {
		log.debug("finding Tblpptborangk instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangk").add(
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
		log.debug("finding Tblpptborangk instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangk as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByFlagSegera(Object flagSegera) {
		return findByProperty(FLAG_SEGERA, flagSegera);
	}

	public List findByIdBorangi(Object idBorangi) {
		return findByProperty(ID_BORANGI, idBorangi);
	}

	public List findByIdBorangg(Object idBorangg) {
		return findByProperty(ID_BORANGG, idBorangg);
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblpptborangk instances");
		try {
			String queryString = "from Tblpptborangk";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangk merge(Tblpptborangk detachedInstance) {
		log.debug("merging Tblpptborangk instance");
		try {
			Tblpptborangk result = (Tblpptborangk) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangk instance) {
		log.debug("attaching dirty Tblpptborangk instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangk instance) {
		log.debug("attaching clean Tblpptborangk instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}