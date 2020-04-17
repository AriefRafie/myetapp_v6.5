package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptulasanteknikal entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptulasanteknikal
 * @author MyEclipse Persistence Tools
 */

public class TblpptulasanteknikalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpptulasanteknikalDAO.class);
	// property constants
	public static final String ID_JABATANTEKNIKAL = "idJabatanteknikal";
	public static final String NO_RUJUKAN_SURAT = "noRujukanSurat";
	public static final String BIL_SURAT = "bilSurat";
	public static final String TEMPOH = "tempoh";
	public static final String KEPUTUSAN = "keputusan";
	public static final String ULASAN = "ulasan";
	public static final String FLAG_TERIMA = "flagTerima";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptulasanteknikal transientInstance) {
		log.debug("saving Tblpptulasanteknikal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptulasanteknikal persistentInstance) {
		log.debug("deleting Tblpptulasanteknikal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptulasanteknikal findById(java.lang.Long id) {
		log.debug("getting Tblpptulasanteknikal instance with id: " + id);
		try {
			Tblpptulasanteknikal instance = (Tblpptulasanteknikal) getSession()
					.get("ekptg.model.entities.Tblpptulasanteknikal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptulasanteknikal instance) {
		log.debug("finding Tblpptulasanteknikal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptulasanteknikal").add(
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
		log.debug("finding Tblpptulasanteknikal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptulasanteknikal as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdJabatanteknikal(Object idJabatanteknikal) {
		return findByProperty(ID_JABATANTEKNIKAL, idJabatanteknikal);
	}

	public List findByNoRujukanSurat(Object noRujukanSurat) {
		return findByProperty(NO_RUJUKAN_SURAT, noRujukanSurat);
	}

	public List findByBilSurat(Object bilSurat) {
		return findByProperty(BIL_SURAT, bilSurat);
	}

	public List findByTempoh(Object tempoh) {
		return findByProperty(TEMPOH, tempoh);
	}

	public List findByKeputusan(Object keputusan) {
		return findByProperty(KEPUTUSAN, keputusan);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByFlagTerima(Object flagTerima) {
		return findByProperty(FLAG_TERIMA, flagTerima);
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
		log.debug("finding all Tblpptulasanteknikal instances");
		try {
			String queryString = "from Tblpptulasanteknikal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptulasanteknikal merge(Tblpptulasanteknikal detachedInstance) {
		log.debug("merging Tblpptulasanteknikal instance");
		try {
			Tblpptulasanteknikal result = (Tblpptulasanteknikal) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptulasanteknikal instance) {
		log.debug("attaching dirty Tblpptulasanteknikal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptulasanteknikal instance) {
		log.debug("attaching clean Tblpptulasanteknikal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}