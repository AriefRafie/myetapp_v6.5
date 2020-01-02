package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptnotisawam entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptnotisawam
 * @author MyEclipse Persistence Tools
 */

public class TblpptnotisawamDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptnotisawamDAO.class);
	// property constants
	public static final String TEMPAT = "tempat	";
	public static final String JENIS_TEMPAT_TAMPAL = "jenisTempatTampal	";
	public static final String ID_JENIS_DOKUMEN = "idJenisDokumen	";
	public static final String BIL_SURAT = "bilSurat	";
	public static final String BIL_NOTIS = "bilNotis	";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptnotisawam transientInstance) {
		log.debug("saving Tblpptnotisawam instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptnotisawam persistentInstance) {
		log.debug("deleting Tblpptnotisawam instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptnotisawam findById(java.lang.Long id) {
		log.debug("getting Tblpptnotisawam instance with id: " + id);
		try {
			Tblpptnotisawam instance = (Tblpptnotisawam) getSession().get(
					"ekptg.model.entities.Tblpptnotisawam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptnotisawam instance) {
		log.debug("finding Tblpptnotisawam instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptnotisawam").add(
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
		log.debug("finding Tblpptnotisawam instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptnotisawam as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTempat(Object tempat) {
		return findByProperty(TEMPAT, tempat);
	}

	public List findByJenisTempatTampal(Object jenisTempatTampal) {
		return findByProperty(JENIS_TEMPAT_TAMPAL, jenisTempatTampal);
	}

	public List findByIdJenisDokumen(Object idJenisDokumen) {
		return findByProperty(ID_JENIS_DOKUMEN, idJenisDokumen);
	}

	public List findByBilSurat(Object bilSurat) {
		return findByProperty(BIL_SURAT, bilSurat);
	}

	public List findByBilNotis(Object bilNotis) {
		return findByProperty(BIL_NOTIS, bilNotis);
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
		log.debug("finding all Tblpptnotisawam instances");
		try {
			String queryString = "from Tblpptnotisawam";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptnotisawam merge(Tblpptnotisawam detachedInstance) {
		log.debug("merging Tblpptnotisawam instance");
		try {
			Tblpptnotisawam result = (Tblpptnotisawam) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptnotisawam instance) {
		log.debug("attaching dirty Tblpptnotisawam instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptnotisawam instance) {
		log.debug("attaching clean Tblpptnotisawam instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}