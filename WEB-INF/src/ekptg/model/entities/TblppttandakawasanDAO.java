package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppttandakawasan entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppttandakawasan
 * @author MyEclipse Persistence Tools
 */

public class TblppttandakawasanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppttandakawasanDAO.class);
	// property constants
	public static final String CARA_LAKSANA = "caraLaksana";
	public static final String NAMA_PEGAWAI = "namaPegawai";
	public static final String FLAG_TANDA = "flagTanda";
	public static final String ALAMAT_JURUUKUR = "alamatJuruukur";
	public static final String ULASAN = "ulasan";
	public static final String NO_RUJAGENSI = "noRujagensi";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblppttandakawasan transientInstance) {
		log.debug("saving Tblppttandakawasan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppttandakawasan persistentInstance) {
		log.debug("deleting Tblppttandakawasan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppttandakawasan findById(java.lang.Long id) {
		log.debug("getting Tblppttandakawasan instance with id: " + id);
		try {
			Tblppttandakawasan instance = (Tblppttandakawasan) getSession()
					.get("ekptg.model.entities.Tblppttandakawasan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppttandakawasan instance) {
		log.debug("finding Tblppttandakawasan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppttandakawasan").add(
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
		log.debug("finding Tblppttandakawasan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppttandakawasan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCaraLaksana(Object caraLaksana) {
		return findByProperty(CARA_LAKSANA, caraLaksana);
	}

	public List findByNamaPegawai(Object namaPegawai) {
		return findByProperty(NAMA_PEGAWAI, namaPegawai);
	}

	public List findByFlagTanda(Object flagTanda) {
		return findByProperty(FLAG_TANDA, flagTanda);
	}

	public List findByAlamatJuruukur(Object alamatJuruukur) {
		return findByProperty(ALAMAT_JURUUKUR, alamatJuruukur);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByNoRujagensi(Object noRujagensi) {
		return findByProperty(NO_RUJAGENSI, noRujagensi);
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
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
		log.debug("finding all Tblppttandakawasan instances");
		try {
			String queryString = "from Tblppttandakawasan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppttandakawasan merge(Tblppttandakawasan detachedInstance) {
		log.debug("merging Tblppttandakawasan instance");
		try {
			Tblppttandakawasan result = (Tblppttandakawasan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppttandakawasan instance) {
		log.debug("attaching dirty Tblppttandakawasan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppttandakawasan instance) {
		log.debug("attaching clean Tblppttandakawasan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}