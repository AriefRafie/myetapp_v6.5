package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkborangp entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkborangp
 * @author MyEclipse Persistence Tools
 */

public class TblppkborangpDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppkborangpDAO.class);
	// property constants
	public static final String NO_FAIL_TERDAHULU = "noFailTerdahulu";
	public static final String JUMLAH_HTA_TARIKHMOHON = "jumlahHtaTarikhmohon";
	public static final String JUMLAH_HTA_TARIKHMATI = "jumlahHtaTarikhmati";
	public static final String JUMLAH_HA_TARIKHMOHON = "jumlahHaTarikhmohon";
	public static final String JUMLAH_HA_TARIKHMATI = "jumlahHaTarikhmati";
	public static final String JUMLAH_HARTA_TARIKHMOHON = "jumlahHartaTarikhmohon";
	public static final String JUMLAH_HARTA_TARIKHMATI = "jumlahHartaTarikhmati";
	public static final String CATATAN = "catatan";
	public static final String ID_NEGERIMHN = "idNegerimhn";
	public static final String ID_DAERAHMHN = "idDaerahmhn";
	public static final String BATAL_KUASA_PENTADBIR = "batalKuasaPentadbir";
	public static final String LANTIK_PENTADBIR_BARU = "lantikPentadbirBaru";
	public static final String BATAL_PAMANAH = "batalPAmanah";
	public static final String LANTIK_PAMANAH = "lantikPAmanah";
	public static final String HARTA_TINGGAL = "hartaTinggal";
	public static final String NILAI_TERDAHULU = "nilaiTerdahulu";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkborangp transientInstance) {
		log.debug("saving Tblppkborangp instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkborangp persistentInstance) {
		log.debug("deleting Tblppkborangp instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkborangp findById(java.lang.Long id) {
		log.debug("getting Tblppkborangp instance with id: " + id);
		try {
			Tblppkborangp instance = (Tblppkborangp) getSession().get(
					"ekptg.model.entities.Tblppkborangp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkborangp instance) {
		log.debug("finding Tblppkborangp instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkborangp").add(
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
		log.debug("finding Tblppkborangp instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkborangp as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoFailTerdahulu(Object noFailTerdahulu) {
		return findByProperty(NO_FAIL_TERDAHULU, noFailTerdahulu);
	}

	public List findByJumlahHtaTarikhmohon(Object jumlahHtaTarikhmohon) {
		return findByProperty(JUMLAH_HTA_TARIKHMOHON, jumlahHtaTarikhmohon);
	}

	public List findByJumlahHtaTarikhmati(Object jumlahHtaTarikhmati) {
		return findByProperty(JUMLAH_HTA_TARIKHMATI, jumlahHtaTarikhmati);
	}

	public List findByJumlahHaTarikhmohon(Object jumlahHaTarikhmohon) {
		return findByProperty(JUMLAH_HA_TARIKHMOHON, jumlahHaTarikhmohon);
	}

	public List findByJumlahHaTarikhmati(Object jumlahHaTarikhmati) {
		return findByProperty(JUMLAH_HA_TARIKHMATI, jumlahHaTarikhmati);
	}

	public List findByJumlahHartaTarikhmohon(Object jumlahHartaTarikhmohon) {
		return findByProperty(JUMLAH_HARTA_TARIKHMOHON, jumlahHartaTarikhmohon);
	}

	public List findByJumlahHartaTarikhmati(Object jumlahHartaTarikhmati) {
		return findByProperty(JUMLAH_HARTA_TARIKHMATI, jumlahHartaTarikhmati);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdNegerimhn(Object idNegerimhn) {
		return findByProperty(ID_NEGERIMHN, idNegerimhn);
	}

	public List findByIdDaerahmhn(Object idDaerahmhn) {
		return findByProperty(ID_DAERAHMHN, idDaerahmhn);
	}

	public List findByBatalKuasaPentadbir(Object batalKuasaPentadbir) {
		return findByProperty(BATAL_KUASA_PENTADBIR, batalKuasaPentadbir);
	}

	public List findByLantikPentadbirBaru(Object lantikPentadbirBaru) {
		return findByProperty(LANTIK_PENTADBIR_BARU, lantikPentadbirBaru);
	}

	public List findByBatalPAmanah(Object batalPAmanah) {
		return findByProperty(BATAL_PAMANAH, batalPAmanah);
	}

	public List findByLantikPAmanah(Object lantikPAmanah) {
		return findByProperty(LANTIK_PAMANAH, lantikPAmanah);
	}

	public List findByHartaTinggal(Object hartaTinggal) {
		return findByProperty(HARTA_TINGGAL, hartaTinggal);
	}

	public List findByNilaiTerdahulu(Object nilaiTerdahulu) {
		return findByProperty(NILAI_TERDAHULU, nilaiTerdahulu);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkborangp instances");
		try {
			String queryString = "from Tblppkborangp";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkborangp merge(Tblppkborangp detachedInstance) {
		log.debug("merging Tblppkborangp instance");
		try {
			Tblppkborangp result = (Tblppkborangp) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkborangp instance) {
		log.debug("attaching dirty Tblppkborangp instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkborangp instance) {
		log.debug("attaching clean Tblppkborangp instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}