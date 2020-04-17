package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkborangpha entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkborangpha
 * @author MyEclipse Persistence Tools
 */

public class TblppkborangphaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkborangphaDAO.class);
	// property constants
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String BIL = "bil";
	public static final String JENAMA = "jenama";
	public static final String NO_DAFTAR = "noDaftar";
	public static final String NO_SIJIL = "noSijil";
	public static final String BIL_UNIT = "bilUnit";
	public static final String ALAMAT_HA1 = "alamatHa1";
	public static final String ALAMAT_HA2 = "alamatHa2";
	public static final String ALAMAT_HA3 = "alamatHa3";
	public static final String POSKOD = "poskod";
	public static final String NILAI_HA_TARIKHMOHON = "nilaiHaTarikhmohon";
	public static final String NILAI_HA_TARIKHMATI = "nilaiHaTarikhmati";
	public static final String NILAI_SEUNIT = "nilaiSeunit";
	public static final String BA_SIMATI = "baSimati";
	public static final String BB_SIMATI = "bbSimati";
	public static final String CATATAN = "catatan";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkborangpha transientInstance) {
		log.debug("saving Tblppkborangpha instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkborangpha persistentInstance) {
		log.debug("deleting Tblppkborangpha instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkborangpha findById(java.lang.Long id) {
		log.debug("getting Tblppkborangpha instance with id: " + id);
		try {
			Tblppkborangpha instance = (Tblppkborangpha) getSession().get(
					"ekptg.model.entities.Tblppkborangpha", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkborangpha instance) {
		log.debug("finding Tblppkborangpha instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkborangpha").add(
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
		log.debug("finding Tblppkborangpha instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkborangpha as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByBil(Object bil) {
		return findByProperty(BIL, bil);
	}

	public List findByJenama(Object jenama) {
		return findByProperty(JENAMA, jenama);
	}

	public List findByNoDaftar(Object noDaftar) {
		return findByProperty(NO_DAFTAR, noDaftar);
	}

	public List findByNoSijil(Object noSijil) {
		return findByProperty(NO_SIJIL, noSijil);
	}

	public List findByBilUnit(Object bilUnit) {
		return findByProperty(BIL_UNIT, bilUnit);
	}

	public List findByAlamatHa1(Object alamatHa1) {
		return findByProperty(ALAMAT_HA1, alamatHa1);
	}

	public List findByAlamatHa2(Object alamatHa2) {
		return findByProperty(ALAMAT_HA2, alamatHa2);
	}

	public List findByAlamatHa3(Object alamatHa3) {
		return findByProperty(ALAMAT_HA3, alamatHa3);
	}

	public List findByPoskod(Object poskod) {
		return findByProperty(POSKOD, poskod);
	}

	public List findByNilaiHaTarikhmohon(Object nilaiHaTarikhmohon) {
		return findByProperty(NILAI_HA_TARIKHMOHON, nilaiHaTarikhmohon);
	}

	public List findByNilaiHaTarikhmati(Object nilaiHaTarikhmati) {
		return findByProperty(NILAI_HA_TARIKHMATI, nilaiHaTarikhmati);
	}

	public List findByNilaiSeunit(Object nilaiSeunit) {
		return findByProperty(NILAI_SEUNIT, nilaiSeunit);
	}

	public List findByBaSimati(Object baSimati) {
		return findByProperty(BA_SIMATI, baSimati);
	}

	public List findByBbSimati(Object bbSimati) {
		return findByProperty(BB_SIMATI, bbSimati);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkborangpha instances");
		try {
			String queryString = "from Tblppkborangpha";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkborangpha merge(Tblppkborangpha detachedInstance) {
		log.debug("merging Tblppkborangpha instance");
		try {
			Tblppkborangpha result = (Tblppkborangpha) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkborangpha instance) {
		log.debug("attaching dirty Tblppkborangpha instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkborangpha instance) {
		log.debug("attaching clean Tblppkborangpha instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}