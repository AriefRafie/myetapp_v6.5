package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangq entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangq
 * @author MyEclipse Persistence Tools
 */

public class TblpptborangqDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptborangqDAO.class);
	// property constants
	public static final String NO_BORANGQ = "noBorangq";
	public static final String FLAG_NOTIS = "flagNotis";
	public static final String ID_UNITLUAS = "idUnitluas";
	public static final String LUAS_SEWA = "luasSewa";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String TEMPOH_PENDUDUKAN = "tempohPendudukan";
	public static final String UNIT_TEMPOH = "unitTempoh";
	public static final String TAWARAN_PAMPASAN = "tawaranPampasan";
	public static final String MASA = "masa";
	public static final String TEMPAT = "tempat";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangq transientInstance) {
		log.debug("saving Tblpptborangq instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangq persistentInstance) {
		log.debug("deleting Tblpptborangq instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangq findById(java.lang.Long id) {
		log.debug("getting Tblpptborangq instance with id: " + id);
		try {
			Tblpptborangq instance = (Tblpptborangq) getSession().get(
					"ekptg.model.entities.Tblpptborangq", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangq instance) {
		log.debug("finding Tblpptborangq instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangq").add(
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
		log.debug("finding Tblpptborangq instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangq as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoBorangq(Object noBorangq) {
		return findByProperty(NO_BORANGQ, noBorangq);
	}

	public List findByFlagNotis(Object flagNotis) {
		return findByProperty(FLAG_NOTIS, flagNotis);
	}

	public List findByIdUnitluas(Object idUnitluas) {
		return findByProperty(ID_UNITLUAS, idUnitluas);
	}

	public List findByLuasSewa(Object luasSewa) {
		return findByProperty(LUAS_SEWA, luasSewa);
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByTempohPendudukan(Object tempohPendudukan) {
		return findByProperty(TEMPOH_PENDUDUKAN, tempohPendudukan);
	}

	public List findByUnitTempoh(Object unitTempoh) {
		return findByProperty(UNIT_TEMPOH, unitTempoh);
	}

	public List findByTawaranPampasan(Object tawaranPampasan) {
		return findByProperty(TAWARAN_PAMPASAN, tawaranPampasan);
	}

	public List findByMasa(Object masa) {
		return findByProperty(MASA, masa);
	}

	public List findByTempat(Object tempat) {
		return findByProperty(TEMPAT, tempat);
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
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
		log.debug("finding all Tblpptborangq instances");
		try {
			String queryString = "from Tblpptborangq";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangq merge(Tblpptborangq detachedInstance) {
		log.debug("merging Tblpptborangq instance");
		try {
			Tblpptborangq result = (Tblpptborangq) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangq instance) {
		log.debug("attaching dirty Tblpptborangq instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangq instance) {
		log.debug("attaching clean Tblpptborangq instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}