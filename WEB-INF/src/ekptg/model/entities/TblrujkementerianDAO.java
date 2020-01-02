package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujkementerian entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujkementerian
 * @author MyEclipse Persistence Tools
 */

public class TblrujkementerianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblrujkementerianDAO.class);
	// property constants
	public static final String KOD_KEMENTERIAN = "kodKementerian";
	public static final String NAMA_KEMENTERIAN = "namaKementerian";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String ID_NEGERI = "idNegeri";
	public static final String JAWATAN = "jawatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujkementerian transientInstance) {
		log.debug("saving Tblrujkementerian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujkementerian persistentInstance) {
		log.debug("deleting Tblrujkementerian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujkementerian findById(java.lang.Long id) {
		log.debug("getting Tblrujkementerian instance with id: " + id);
		try {
			Tblrujkementerian instance = (Tblrujkementerian) getSession().get(
					"ekptg.model.entities.Tblrujkementerian", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujkementerian instance) {
		log.debug("finding Tblrujkementerian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujkementerian").add(
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
		log.debug("finding Tblrujkementerian instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujkementerian as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByKodKementerian(Object kodKementerian) {
		return findByProperty(KOD_KEMENTERIAN, kodKementerian);
	}

	public List findByNamaKementerian(Object namaKementerian) {
		return findByProperty(NAMA_KEMENTERIAN, namaKementerian);
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

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujkementerian instances");
		try {
			String queryString = "from Tblrujkementerian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujkementerian merge(Tblrujkementerian detachedInstance) {
		log.debug("merging Tblrujkementerian instance");
		try {
			Tblrujkementerian result = (Tblrujkementerian) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujkementerian instance) {
		log.debug("attaching dirty Tblrujkementerian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujkementerian instance) {
		log.debug("attaching clean Tblrujkementerian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}