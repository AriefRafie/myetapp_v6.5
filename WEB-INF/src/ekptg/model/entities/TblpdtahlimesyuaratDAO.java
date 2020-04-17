package ekptg.model.entities;



import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtahlimesyuarat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtahlimesyuarat
 * @author MyEclipse Persistence Tools
 */

public class TblpdtahlimesyuaratDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpdtahlimesyuaratDAO.class);
	// property constants
	
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String EMEL = "emel";
	public static final String NO_TEL = "noTel";
	public static final String NO_FAKS = "noFaks";
        public static final String NAMA_AHLIMESYUARAT = "namaAhlimesyuara";
        public static final String JAWATAN = "jawatan";
        public static final String FLAG_PENGERUSI = "flagPengerusi";

	public void save(Tblpdtahlimesyuarat transientInstance) {
		log.debug("saving Tblpdtahlimesyuarat instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtahlimesyuarat persistentInstance) {
		log.debug("deleting Tblpdtahlimesyuarat instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtahlimesyuarat findById(java.math.BigDecimal id) {
		log.debug("getting Tblpdtahlimesyuarat instance with id: " + id);
		try {
			Tblpdtahlimesyuarat instance = (Tblpdtahlimesyuarat) getSession()
					.get("ekptg.model.entities.Tblpdtahlimesyuarat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtahlimesyuarat instance) {
		log.debug("finding Tblpdtahlimesyuarat instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtahlimesyuarat").add(
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
		log.debug("finding Tblpdtahlimesyuarat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtahlimesyuarat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByEmel(Object emel) {
		return findByProperty(EMEL, emel);
	}

	public List findByNoTel(Object noTel) {
		return findByProperty(NO_TEL, noTel);
	}

	public List findByNoFaks(Object noFaks) {
		return findByProperty(NO_FAKS, noFaks);
	}
        public List findByNamaAhlimesyuarat(Object namaAhlimesyuarat) {
                return findByProperty(NAMA_AHLIMESYUARAT, namaAhlimesyuarat);
        }
        public List findByJawatan(Object jawatan) {
                return findByProperty(JAWATAN, jawatan);
        }
        public List findByFlagPengerusi(Object flagPengerusi) {
                return findByProperty(FLAG_PENGERUSI, flagPengerusi);
        }
	public List findAll() {
		log.debug("finding all Tblpdtahlimesyuarat instances");
		try {
			String queryString = "from Tblpdtahlimesyuarat";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtahlimesyuarat merge(Tblpdtahlimesyuarat detachedInstance) {
		log.debug("merging Tblpdtahlimesyuarat instance");
		try {
			Tblpdtahlimesyuarat result = (Tblpdtahlimesyuarat) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtahlimesyuarat instance) {
		log.debug("attaching dirty Tblpdtahlimesyuarat instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtahlimesyuarat instance) {
		log.debug("attaching clean Tblpdtahlimesyuarat instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}