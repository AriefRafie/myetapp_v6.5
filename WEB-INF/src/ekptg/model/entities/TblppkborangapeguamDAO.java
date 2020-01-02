package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkborangapeguam entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkborangapeguam
 * @author MyEclipse Persistence Tools
 */

public class TblppkborangapeguamDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkborangapeguamDAO.class);
	// property constants
	public static final String NAMA_FIRMA = "namaFirma";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_RUJUKAN_FIRMA = "noRujukanFirma";
	public static final String NO_TEL = "noTel";
	public static final String NO_FAX = "noFax";
	public static final String NAMA_PEGUAM = "namaPeguam";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkborangapeguam transientInstance) {
		log.debug("saving Tblppkborangapeguam instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkborangapeguam persistentInstance) {
		log.debug("deleting Tblppkborangapeguam instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkborangapeguam findById(java.lang.Long id) {
		log.debug("getting Tblppkborangapeguam instance with id: " + id);
		try {
			Tblppkborangapeguam instance = (Tblppkborangapeguam) getSession()
					.get("ekptg.model.entities.Tblppkborangapeguam", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkborangapeguam instance) {
		log.debug("finding Tblppkborangapeguam instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkborangapeguam").add(
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
		log.debug("finding Tblppkborangapeguam instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkborangapeguam as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaFirma(Object namaFirma) {
		return findByProperty(NAMA_FIRMA, namaFirma);
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

	public List findByNoRujukanFirma(Object noRujukanFirma) {
		return findByProperty(NO_RUJUKAN_FIRMA, noRujukanFirma);
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
		log.debug("finding all Tblppkborangapeguam instances");
		try {
			String queryString = "from Tblppkborangapeguam";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkborangapeguam merge(Tblppkborangapeguam detachedInstance) {
		log.debug("merging Tblppkborangapeguam instance");
		try {
			Tblppkborangapeguam result = (Tblppkborangapeguam) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkborangapeguam instance) {
		log.debug("attaching dirty Tblppkborangapeguam instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkborangapeguam instance) {
		log.debug("attaching clean Tblppkborangapeguam instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}