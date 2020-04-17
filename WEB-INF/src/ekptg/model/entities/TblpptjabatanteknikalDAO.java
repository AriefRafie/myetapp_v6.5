package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptjabatanteknikal entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptjabatanteknikal
 * @author MyEclipse Persistence Tools
 */

public class TblpptjabatanteknikalDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpptjabatanteknikalDAO.class);
	// property constants
	public static final String NAMA_JABATAN = "namaJabatan";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String NO_TEL = "noTel";
	public static final String NO_FAX = "noFax";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptjabatanteknikal transientInstance) {
		log.debug("saving Tblpptjabatanteknikal instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptjabatanteknikal persistentInstance) {
		log.debug("deleting Tblpptjabatanteknikal instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptjabatanteknikal findById(java.lang.Long id) {
		log.debug("getting Tblpptjabatanteknikal instance with id: " + id);
		try {
			Tblpptjabatanteknikal instance = (Tblpptjabatanteknikal) getSession()
					.get("ekptg.model.entities.Tblpptjabatanteknikal", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptjabatanteknikal instance) {
		log.debug("finding Tblpptjabatanteknikal instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptjabatanteknikal").add(
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
		log.debug("finding Tblpptjabatanteknikal instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptjabatanteknikal as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNamaJabatan(Object namaJabatan) {
		return findByProperty(NAMA_JABATAN, namaJabatan);
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

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblpptjabatanteknikal instances");
		try {
			String queryString = "from Tblpptjabatanteknikal";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptjabatanteknikal merge(Tblpptjabatanteknikal detachedInstance) {
		log.debug("merging Tblpptjabatanteknikal instance");
		try {
			Tblpptjabatanteknikal result = (Tblpptjabatanteknikal) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptjabatanteknikal instance) {
		log.debug("attaching dirty Tblpptjabatanteknikal instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptjabatanteknikal instance) {
		log.debug("attaching clean Tblpptjabatanteknikal instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}