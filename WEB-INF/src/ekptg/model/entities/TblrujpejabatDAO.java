package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblrujpejabat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblrujpejabat
 * @author MyEclipse Persistence Tools
 */

public class TblrujpejabatDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblrujpejabatDAO.class);
	// property constants
        public static final String ID_SEKSYEN = "idSeksyen";
        public static final String ID_NEGERI = "idNegeri";
        public static final String ID_DAERAH = "idDaerah";
	public static final String JENIS_PEJABAT = "jenisPejabat";
	public static final String NAMA_PEJABAT = "namaPejabat";
	public static final String ALAMAT1 = "alamat1";
	public static final String ALAMAT2 = "alamat2";
	public static final String ALAMAT3 = "alamat3";
	public static final String POSKOD = "poskod";
	public static final String NO_TEL = "noTel";
	public static final String NO_FAX = "noFax";
	public static final String JAWATAN = "jawatan";
	public static final String NO_AKAUN = "noAkaun";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblrujpejabat transientInstance) {
		log.debug("saving Tblrujpejabat instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblrujpejabat persistentInstance) {
		log.debug("deleting Tblrujpejabat instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblrujpejabat findById(java.lang.Long id) {
		log.debug("getting Tblrujpejabat instance with id: " + id);
		try {
			Tblrujpejabat instance = (Tblrujpejabat) getSession().get(
					"ekptg.model.entities.Tblrujpejabat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblrujpejabat instance) {
		log.debug("finding Tblrujpejabat instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblrujpejabat").add(
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
		log.debug("finding Tblrujpejabat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblrujpejabat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}
        
        public List findByIdSeksyen(Object idSeksyen) {
                return findByProperty(ID_SEKSYEN, idSeksyen);
        }
        
        public List findByIdNegeri(Object idNegeri) {
                return findByProperty(ID_NEGERI, idNegeri);
        }
    
        public List findByIdDaerah(Object idDaerah) {
                return findByProperty(ID_DAERAH, idDaerah);
        }

	public List findByJenisPejabat(Object jenisPejabat) {
		return findByProperty(JENIS_PEJABAT, jenisPejabat);
	}

	public List findByNamaPejabat(Object namaPejabat) {
		return findByProperty(NAMA_PEJABAT, namaPejabat);
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

	public List findByNoTel(Object noTel) {
		return findByProperty(NO_TEL, noTel);
	}

	public List findByNoFax(Object noFax) {
		return findByProperty(NO_FAX, noFax);
	}

	public List findByJawatan(Object jawatan) {
		return findByProperty(JAWATAN, jawatan);
	}

	public List findByNoAkaun(Object noAkaun) {
		return findByProperty(NO_AKAUN, noAkaun);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblrujpejabat instances");
		try {
			String queryString = "from Tblrujpejabat";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblrujpejabat merge(Tblrujpejabat detachedInstance) {
		log.debug("merging Tblrujpejabat instance");
		try {
			Tblrujpejabat result = (Tblrujpejabat) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblrujpejabat instance) {
		log.debug("attaching dirty Tblrujpejabat instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblrujpejabat instance) {
		log.debug("attaching clean Tblrujpejabat instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}