package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppeguam entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppeguam
 * @author MyEclipse Persistence Tools
 */

public class TblhtppeguamDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtppeguamDAO.class);
	// property constants
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String NO_RUJUKAN = "noRujukan";
	public static final String NAMA = "nama";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_TEL = "noTel";
	public static final String NO_FAX = "noFax";
	public static final String NAMA_PEGUAM = "namaPeguam";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtppeguam transientInstance) {
		log.debug("saving Tblhtppeguam instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppeguam persistentInstance) {
		log.debug("deleting Tblhtppeguam instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppeguam findById(java.lang.Long id) {
		log.debug("getting Tblhtppeguam instance with id: " + id);
		try {
			Tblhtppeguam instance = (Tblhtppeguam) getSession().get(
					"ekptg.model.entities.Tblhtppeguam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppeguam instance) {
		log.debug("finding Tblhtppeguam instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppeguam").add(
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
		log.debug("finding Tblhtppeguam instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppeguam as model where model."
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

	public List findByNoRujukan(Object noRujukan) {
		return findByProperty(NO_RUJUKAN, noRujukan);
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

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByNoTel(Object noTel) {
		return findByProperty(NO_TEL, noTel);
	}

	public List findByNoFax(Object noFax) {
		return findByProperty(NO_FAX, noFax);
	}

	public List findByNamaPeguam(Object namaPeguam) {
		return findByProperty(NAMA_PEGUAM, namaPeguam);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtppeguam instances");
		try {
			String queryString = "from Tblhtppeguam";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppeguam merge(Tblhtppeguam detachedInstance) {
		log.debug("merging Tblhtppeguam instance");
		try {
			Tblhtppeguam result = (Tblhtppeguam) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppeguam instance) {
		log.debug("attaching dirty Tblhtppeguam instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppeguam instance) {
		log.debug("attaching clean Tblhtppeguam instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}