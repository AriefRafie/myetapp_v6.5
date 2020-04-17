package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangl entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangl
 * @author MyEclipse Persistence Tools
 */

public class TblpptboranglDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptboranglDAO.class);
	// property constants
	public static final String JENIS_PILIH = "jenisPilih";
	public static final String UNIT_TEMPOH = "unitTempoh";
	public static final String TEMPOH = "tempoh";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangl transientInstance) {
		log.debug("saving Tblpptborangl instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangl persistentInstance) {
		log.debug("deleting Tblpptborangl instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangl findById(java.lang.Long id) {
		log.debug("getting Tblpptborangl instance with id: " + id);
		try {
			Tblpptborangl instance = (Tblpptborangl) getSession().get(
					"ekptg.model.entities.Tblpptborangl", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangl instance) {
		log.debug("finding Tblpptborangl instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangl").add(
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
		log.debug("finding Tblpptborangl instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangl as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByJenisPilih(Object jenisPilih) {
		return findByProperty(JENIS_PILIH, jenisPilih);
	}

	public List findByUnitTempoh(Object unitTempoh) {
		return findByProperty(UNIT_TEMPOH, unitTempoh);
	}

	public List findByTempoh(Object tempoh) {
		return findByProperty(TEMPOH, tempoh);
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
		log.debug("finding all Tblpptborangl instances");
		try {
			String queryString = "from Tblpptborangl";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangl merge(Tblpptborangl detachedInstance) {
		log.debug("merging Tblpptborangl instance");
		try {
			Tblpptborangl result = (Tblpptborangl) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangl instance) {
		log.debug("attaching dirty Tblpptborangl instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangl instance) {
		log.debug("attaching clean Tblpptborangl instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}