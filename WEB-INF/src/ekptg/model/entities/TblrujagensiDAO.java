package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujagensi entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujagensi
 * @author MyEclipse Persistence Tools
 */

public class TblrujagensiDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujagensiDAO.class);
	// property constants
	public static final String KOD_AGENSI = "kodAgensi";
	public static final String NAMA_AGENSI = "namaAgensi";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String JAWATAN = "jawatan";
	public static final String ID_KEMENTERIAN = "idKementerian";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujagensi transientInstance) {
		log.debug("saving Tblrujagensi instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujagensi persistentInstance) {
		log.debug("deleting Tblrujagensi instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujagensi findById(java.lang.Long id) {
		log.debug("getting Tblrujagensi instance with id: " + id);
		try {
			Tblrujagensi instance = (Tblrujagensi) getSession().get(
					"ekptg.model.entities.Tblrujagensi", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujagensi instance) {
		log.debug("finding Tblrujagensi instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujagensi").add(
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
		log.debug("finding Tblrujagensi instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujagensi as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodAgensi(Object kodAgensi) {
		return findByProperty(KOD_AGENSI, kodAgensi);
	}

	public List findByNamaAgensi(Object namaAgensi) {
		return findByProperty(NAMA_AGENSI, namaAgensi);
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

	public List findByJawatan(Object jawatan) {
		return findByProperty(JAWATAN, jawatan);
	}

	public List findByIdKementerian(Object idKementerian) {
		return findByProperty(ID_KEMENTERIAN, idKementerian);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujagensi instances");
		try {
			String queryString = "from Tblrujagensi";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujagensi merge(Tblrujagensi detachedInstance) {
		log.debug("merging Tblrujagensi instance");
		try {
			Tblrujagensi result = (Tblrujagensi) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujagensi instance) {
		log.debug("attaching dirty Tblrujagensi instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujagensi instance) {
		log.debug("attaching clean Tblrujagensi instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}