package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppemaju entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppemaju
 * @author MyEclipse Persistence Tools
 */

public class TblhtppemajuDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtppemajuDAO.class);
	// property constants
	public static final String ID_RUJJENISOPB = "idRujjenisopb";
	public static final String NO_OPB = "noOpb";
	public static final String NO_RUJUKAN_PEMAJU = "noRujukanPemaju";
	public static final String NAMA_PEMAJU = "namaPemaju";
	public static final String ALAMAT_PEMAJU1 = "alamatPemaju1";
	public static final String ALAMAT_PEMAJU2 = "alamatPemaju2";
	public static final String ALAMAT_PEMAJU3 = "alamatPemaju3";
	public static final String POSKOD_PEMAJU = "poskodPemaju";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_TEL = "noTel";
	public static final String NO_FAX = "noFax";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtppemaju transientInstance) {
		log.debug("saving Tblhtppemaju instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppemaju persistentInstance) {
		log.debug("deleting Tblhtppemaju instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppemaju findById(java.lang.Long id) {
		log.debug("getting Tblhtppemaju instance with id: " + id);
		try {
			Tblhtppemaju instance = (Tblhtppemaju) getSession().get(
					"ekptg.model.entities.Tblhtppemaju", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppemaju instance) {
		log.debug("finding Tblhtppemaju instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppemaju").add(
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
		log.debug("finding Tblhtppemaju instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppemaju as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdRujjenisopb(Object idRujjenisopb) {
		return findByProperty(ID_RUJJENISOPB, idRujjenisopb);
	}

	public List findByNoOpb(Object noOpb) {
		return findByProperty(NO_OPB, noOpb);
	}

	public List findByNoRujukanPemaju(Object noRujukanPemaju) {
		return findByProperty(NO_RUJUKAN_PEMAJU, noRujukanPemaju);
	}

	public List findByNamaPemaju(Object namaPemaju) {
		return findByProperty(NAMA_PEMAJU, namaPemaju);
	}

	public List findByAlamatPemaju1(Object alamatPemaju1) {
		return findByProperty(ALAMAT_PEMAJU1, alamatPemaju1);
	}

	public List findByAlamatPemaju2(Object alamatPemaju2) {
		return findByProperty(ALAMAT_PEMAJU2, alamatPemaju2);
	}

	public List findByAlamatPemaju3(Object alamatPemaju3) {
		return findByProperty(ALAMAT_PEMAJU3, alamatPemaju3);
	}

	public List findByPoskodPemaju(Object poskodPemaju) {
		return findByProperty(POSKOD_PEMAJU, poskodPemaju);
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

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtppemaju instances");
		try {
			String queryString = "from Tblhtppemaju";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppemaju merge(Tblhtppemaju detachedInstance) {
		log.debug("merging Tblhtppemaju instance");
		try {
			Tblhtppemaju result = (Tblhtppemaju) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppemaju instance) {
		log.debug("attaching dirty Tblhtppemaju instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppemaju instance) {
		log.debug("attaching clean Tblhtppemaju instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}