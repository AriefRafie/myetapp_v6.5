package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblsemakanhantar entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblsemakanhantar
 * @author MyEclipse Persistence Tools
 */

public class TblsemakanhantarDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblsemakanhantarDAO.class);
	// property constants
	public static final String ID_SEMAKANSENARAI = "idSemakansenarai";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String PEMOHON = "pemohon";
	public static final String PENTADBIR = "pentadbir";
	public static final String STATUS = "status";
	public static final String CATATAN = "catatan";
	public static final String ID_DOKUMEN = "idDokumen";
	public static final String FLAG_ADA = "flagAda";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblsemakanhantar transientInstance) {
		log.debug("saving Tblsemakanhantar instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblsemakanhantar persistentInstance) {
		log.debug("deleting Tblsemakanhantar instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblsemakanhantar findById(java.lang.Long id) {
		log.debug("getting Tblsemakanhantar instance with id: " + id);
		try {
			Tblsemakanhantar instance = (Tblsemakanhantar) getSession().get(
					"ekptg.model.entities.Tblsemakanhantar", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblsemakanhantar instance) {
		log.debug("finding Tblsemakanhantar instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblsemakanhantar").add(
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
		log.debug("finding Tblsemakanhantar instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblsemakanhantar as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdSemakansenarai(Object idSemakansenarai) {
		return findByProperty(ID_SEMAKANSENARAI, idSemakansenarai);
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByPemohon(Object pemohon) {
		return findByProperty(PEMOHON, pemohon);
	}

	public List findByPentadbir(Object pentadbir) {
		return findByProperty(PENTADBIR, pentadbir);
	}

	public List findByStatus(Object status) {
		return findByProperty(STATUS, status);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdDokumen(Object idDokumen) {
		return findByProperty(ID_DOKUMEN, idDokumen);
	}

	public List findByFlagAda(Object flagAda) {
		return findByProperty(FLAG_ADA, flagAda);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblsemakanhantar instances");
		try {
			String queryString = "from Tblsemakanhantar";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblsemakanhantar merge(Tblsemakanhantar detachedInstance) {
		log.debug("merging Tblsemakanhantar instance");
		try {
			Tblsemakanhantar result = (Tblsemakanhantar) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblsemakanhantar instance) {
		log.debug("attaching dirty Tblsemakanhantar instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblsemakanhantar instance) {
		log.debug("attaching clean Tblsemakanhantar instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}