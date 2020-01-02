package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangj entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangj
 * @author MyEclipse Persistence Tools
 */

public class TblpptborangjDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptborangjDAO.class);
	// property constants
	public static final String ID_BANGUNAN = "idBangunan";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_BORANGI = "idBorangi";
	public static final String TEMPOH = "tempoh";
	public static final String UNIT_TEMPOH = "unitTempoh";
	public static final String TINDAKAN = "tindakan";
	public static final String KEPUTUSAN = "keputusan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangj transientInstance) {
		log.debug("saving Tblpptborangj instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangj persistentInstance) {
		log.debug("deleting Tblpptborangj instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangj findById(java.lang.Long id) {
		log.debug("getting Tblpptborangj instance with id: " + id);
		try {
			Tblpptborangj instance = (Tblpptborangj) getSession().get(
					"ekptg.model.entities.Tblpptborangj", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangj instance) {
		log.debug("finding Tblpptborangj instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangj").add(
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
		log.debug("finding Tblpptborangj instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangj as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdBangunan(Object idBangunan) {
		return findByProperty(ID_BANGUNAN, idBangunan);
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByIdBorangi(Object idBorangi) {
		return findByProperty(ID_BORANGI, idBorangi);
	}

	public List findByTempoh(Object tempoh) {
		return findByProperty(TEMPOH, tempoh);
	}

	public List findByUnitTempoh(Object unitTempoh) {
		return findByProperty(UNIT_TEMPOH, unitTempoh);
	}

	public List findByTindakan(Object tindakan) {
		return findByProperty(TINDAKAN, tindakan);
	}

	public List findByKeputusan(Object keputusan) {
		return findByProperty(KEPUTUSAN, keputusan);
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
		log.debug("finding all Tblpptborangj instances");
		try {
			String queryString = "from Tblpptborangj";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangj merge(Tblpptborangj detachedInstance) {
		log.debug("merging Tblpptborangj instance");
		try {
			Tblpptborangj result = (Tblpptborangj) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangj instance) {
		log.debug("attaching dirty Tblpptborangj instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangj instance) {
		log.debug("attaching clean Tblpptborangj instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}