package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppttambahaward entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppttambahaward
 * @author MyEclipse Persistence Tools
 */

public class TblppttambahawardDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppttambahawardDAO.class);
	// property constants
	public static final String ID_AWARD = "idAward";
	public static final String NILAI_AWARD = "nilaiAward";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblppttambahaward transientInstance) {
		log.debug("saving Tblppttambahaward instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppttambahaward persistentInstance) {
		log.debug("deleting Tblppttambahaward instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppttambahaward findById(java.lang.Long id) {
		log.debug("getting Tblppttambahaward instance with id: " + id);
		try {
			Tblppttambahaward instance = (Tblppttambahaward) getSession().get(
					"ekptg.model.entities.Tblppttambahaward", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppttambahaward instance) {
		log.debug("finding Tblppttambahaward instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppttambahaward").add(
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
		log.debug("finding Tblppttambahaward instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppttambahaward as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdAward(Object idAward) {
		return findByProperty(ID_AWARD, idAward);
	}

	public List findByNilaiAward(Object nilaiAward) {
		return findByProperty(NILAI_AWARD, nilaiAward);
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

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblppttambahaward instances");
		try {
			String queryString = "from Tblppttambahaward";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppttambahaward merge(Tblppttambahaward detachedInstance) {
		log.debug("merging Tblppttambahaward instance");
		try {
			Tblppttambahaward result = (Tblppttambahaward) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppttambahaward instance) {
		log.debug("attaching dirty Tblppttambahaward instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppttambahaward instance) {
		log.debug("attaching clean Tblppttambahaward instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}