package ekptg.model.entities;


import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpdtmesyuarat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpdtmesyuarat
 * @author MyEclipse Persistence Tools
 */

public class TblpdtmesyuaratDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpdtmesyuaratDAO.class);
	// property constants
	public static final String BIL_MESYUARAT = "bilMesyuarat";
	public static final String TAJUK_MESYUARAT = "tajukMesyuarat";
	public static final String KATEGORI_MESYUARAT = "kategoriMesyuarat";
	public static final String MASA_MESYUARAT_DARI = "masaMesyuaratDari";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
        public static final String CATATAN = "catatan";
        public static final String WAKTU_MESYUARAT_DARI = "waktuMesyuaratDari";
        public static final String MASA_MESYUARAT_HINGGA = "masaMesyuaratHingga";
        public static final String WAKTU_MESYUARAT_HINGGA = "waktuMesyuaratHingga";
        

	public void save(Tblpdtmesyuarat transientInstance) {
		log.debug("saving Tblpdtmesyuarat instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpdtmesyuarat persistentInstance) {
		log.debug("deleting Tblpdtmesyuarat instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpdtmesyuarat findById(java.lang.Long id) {
		log.debug("getting Tblpdtmesyuarat instance with id: " + id);
		try {
			Tblpdtmesyuarat instance = (Tblpdtmesyuarat) getSession().get(
					"ekptg.model.entities.Tblpdtmesyuarat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpdtmesyuarat instance) {
		log.debug("finding Tblpdtmesyuarat instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpdtmesyuarat").add(
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
		log.debug("finding Tblpdtmesyuarat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpdtmesyuarat as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBilMesyuarat(Object bilMesyuarat) {
		return findByProperty(BIL_MESYUARAT, bilMesyuarat);
	}

	public List findByTajukMesyuarat(Object tajukMesyuarat) {
		return findByProperty(TAJUK_MESYUARAT, tajukMesyuarat);
	}

	public List findByKategoriMesyuarat(Object kategoriMesyuarat) {
		return findByProperty(KATEGORI_MESYUARAT, kategoriMesyuarat);
	}

	public List findByMasaMesyuaratDari(Object masaMesyuaratDari) {
		return findByProperty(MASA_MESYUARAT_DARI, masaMesyuaratDari);
	}
	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}
        public List findByCatatan(Object catatan) {
                return findByProperty(CATATAN, catatan);
        }
        public List findByWaktuMesyuaratDari(Object waktuMesyuaratDari) {
                return findByProperty(WAKTU_MESYUARAT_DARI, waktuMesyuaratDari);
        }
        public List findByMasaMesyuaratHingga(Object masaMesyuaratHingga) {
                return findByProperty(MASA_MESYUARAT_HINGGA, masaMesyuaratHingga);
        }
        public List findByWaktuMesyuaratHingga(Object waktuMesyuaratHingga) {
                return findByProperty(WAKTU_MESYUARAT_HINGGA, waktuMesyuaratHingga);
        }
       
	public List findAll() {
		log.debug("finding all Tblpdtmesyuarat instances");
		try {
			String queryString = "from Tblpdtmesyuarat";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpdtmesyuarat merge(Tblpdtmesyuarat detachedInstance) {
		log.debug("merging Tblpdtmesyuarat instance");
		try {
			Tblpdtmesyuarat result = (Tblpdtmesyuarat) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpdtmesyuarat instance) {
		log.debug("attaching dirty Tblpdtmesyuarat instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpdtmesyuarat instance) {
		log.debug("attaching clean Tblpdtmesyuarat instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}