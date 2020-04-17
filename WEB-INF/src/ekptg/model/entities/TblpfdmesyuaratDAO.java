package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpfdmesyuarat entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpfdmesyuarat
 * @author MyEclipse Persistence Tools
 */

public class TblpfdmesyuaratDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpfdmesyuaratDAO.class);
	// property constants
	public static final String BIL_MESYUARAT = "bilMesyuarat";
	public static final String TAJUK_MESYUARAT = "tajukMesyuarat";
	public static final String KATEGORI_MESYUARAT = "kategoriMesyuarat";
	public static final String MASA_MESYUARAT_DARI = "masaMesyuaratDari";
	public static final String ID_LOKASI = "idLokasi";
	public static final String ID_FAIL = "idFail";
	public static final String ID_SEKSYEN = "idSeksyen";
	public static final String ID_PEGAWAI = "idPegawai";
	public static final String CATATAN = "catatan";
	public static final String WAKTU_MESYUARAT_DARI = "waktuMesyuaratDari";
	public static final String MASA_MESYUARAT_HINGGA = "masaMesyuaratHingga";
	public static final String WAKTU_MESYUARAT_HINGGA = "waktuMesyuaratHingga";
	public static final String ID_STATUS = "idStatus";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpfdmesyuarat transientInstance) {
		log.debug("saving Tblpfdmesyuarat instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpfdmesyuarat persistentInstance) {
		log.debug("deleting Tblpfdmesyuarat instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpfdmesyuarat findById(java.lang.Long id) {
		log.debug("getting Tblpfdmesyuarat instance with id: " + id);
		try {
			Tblpfdmesyuarat instance = (Tblpfdmesyuarat) getSession().get(
					"ekptg.model.entities.Tblpfdmesyuarat", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpfdmesyuarat instance) {
		log.debug("finding Tblpfdmesyuarat instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpfdmesyuarat").add(
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
		log.debug("finding Tblpfdmesyuarat instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpfdmesyuarat as model where model."
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

	public List findByIdLokasi(Object idLokasi) {
		return findByProperty(ID_LOKASI, idLokasi);
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findByIdSeksyen(Object idSeksyen) {
		return findByProperty(ID_SEKSYEN, idSeksyen);
	}

	public List findByIdPegawai(Object idPegawai) {
		return findByProperty(ID_PEGAWAI, idPegawai);
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

	public List findByIdStatus(Object idStatus) {
		return findByProperty(ID_STATUS, idStatus);
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
		log.debug("finding all Tblpfdmesyuarat instances");
		try {
			String queryString = "from Tblpfdmesyuarat";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpfdmesyuarat merge(Tblpfdmesyuarat detachedInstance) {
		log.debug("merging Tblpfdmesyuarat instance");
		try {
			Tblpfdmesyuarat result = (Tblpfdmesyuarat) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpfdmesyuarat instance) {
		log.debug("attaching dirty Tblpfdmesyuarat instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpfdmesyuarat instance) {
		log.debug("attaching clean Tblpfdmesyuarat instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}