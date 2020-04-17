package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtppemohon entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtppemohon
 * @author MyEclipse Persistence Tools
 */

public class TblhtppemohonDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtppemohonDAO.class);
	// property constants
	public static final String NO_PEMOHON = "noPemohon";
	public static final String NAMA_PEMOHON = "namaPemohon";
	public static final String ALAMAT_PEMOHON1 = "alamatPemohon1";
	public static final String ALAMAT_PEMOHON2 = "alamatPemohon2";
	public static final String ALAMAT_PEMOHON3 = "alamatPemohon3";
	public static final String POSKOD = "poskod";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_TEL = "noTel";
	public static final String NO_FAX = "noFax";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtppemohon transientInstance) {
		log.debug("saving Tblhtppemohon instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtppemohon persistentInstance) {
		log.debug("deleting Tblhtppemohon instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtppemohon findById(java.lang.Long id) {
		log.debug("getting Tblhtppemohon instance with id: " + id);
		try {
			Tblhtppemohon instance = (Tblhtppemohon) getSession().get(
					"ekptg.model.entities.Tblhtppemohon", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtppemohon instance) {
		log.debug("finding Tblhtppemohon instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtppemohon").add(
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
		log.debug("finding Tblhtppemohon instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtppemohon as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoPemohon(Object noPemohon) {
		return findByProperty(NO_PEMOHON, noPemohon);
	}

	public List findByNamaPemohon(Object namaPemohon) {
		return findByProperty(NAMA_PEMOHON, namaPemohon);
	}

	public List findByAlamatPemohon1(Object alamatPemohon1) {
		return findByProperty(ALAMAT_PEMOHON1, alamatPemohon1);
	}

	public List findByAlamatPemohon2(Object alamatPemohon2) {
		return findByProperty(ALAMAT_PEMOHON2, alamatPemohon2);
	}

	public List findByAlamatPemohon3(Object alamatPemohon3) {
		return findByProperty(ALAMAT_PEMOHON3, alamatPemohon3);
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

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtppemohon instances");
		try {
			String queryString = "from Tblhtppemohon";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtppemohon merge(Tblhtppemohon detachedInstance) {
		log.debug("merging Tblhtppemohon instance");
		try {
			Tblhtppemohon result = (Tblhtppemohon) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtppemohon instance) {
		log.debug("attaching dirty Tblhtppemohon instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtppemohon instance) {
		log.debug("attaching clean Tblhtppemohon instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}