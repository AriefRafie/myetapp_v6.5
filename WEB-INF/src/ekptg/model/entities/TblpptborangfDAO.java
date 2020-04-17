package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptborangf entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptborangf
 * @author MyEclipse Persistence Tools
 */

public class TblpptborangfDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptborangfDAO.class);
	// property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_PIHAKBERKEPENTINGAN = "idPihakberkepentingan";
	public static final String ID_JENISPB = "idJenispb";
	public static final String NAMA = "nama";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String TEMPOH = "tempoh";
	public static final String UNIT_TEMPOH = "unitTempoh";
	public static final String ULASAN = "ulasan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptborangf transientInstance) {
		log.debug("saving Tblpptborangf instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptborangf persistentInstance) {
		log.debug("deleting Tblpptborangf instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptborangf findById(java.lang.Long id) {
		log.debug("getting Tblpptborangf instance with id: " + id);
		try {
			Tblpptborangf instance = (Tblpptborangf) getSession().get(
					"ekptg.model.entities.Tblpptborangf", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptborangf instance) {
		log.debug("finding Tblpptborangf instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptborangf").add(
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
		log.debug("finding Tblpptborangf instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptborangf as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByIdPihakberkepentingan(Object idPihakberkepentingan) {
		return findByProperty(ID_PIHAKBERKEPENTINGAN, idPihakberkepentingan);
	}

	public List findByIdJenispb(Object idJenispb) {
		return findByProperty(ID_JENISPB, idJenispb);
	}

	public List findByNama(Object nama) {
		return findByProperty(NAMA, nama);
	}

	public List findByAlamat1(Object alamat1) {
		return findByProperty(ALAMAT1, alamat1);
	}

	public List findByAlamat2(Object alamat2) {
		return findByProperty(ALAMAT2, alamat2);
	}

	public List findByAlamat3(Object alamat3) {
		return findByProperty(ALAMAT3, alamat3);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByTempoh(Object tempoh) {
		return findByProperty(TEMPOH, tempoh);
	}

	public List findByUnitTempoh(Object unitTempoh) {
		return findByProperty(UNIT_TEMPOH, unitTempoh);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
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
		log.debug("finding all Tblpptborangf instances");
		try {
			String queryString = "from Tblpptborangf";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptborangf merge(Tblpptborangf detachedInstance) {
		log.debug("merging Tblpptborangf instance");
		try {
			Tblpptborangf result = (Tblpptborangf) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptborangf instance) {
		log.debug("attaching dirty Tblpptborangf instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptborangf instance) {
		log.debug("attaching clean Tblpptborangf instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}