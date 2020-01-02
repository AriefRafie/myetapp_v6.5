package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpbil entities. Transaction control of the save(), update() and delete()
 * operations can directly support Spring container-managed transactions or they
 * can be augmented to handle user-managed Spring transactions. Each of these
 * methods provides additional information for how to configure it for the
 * desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblphpbil
 * @author MyEclipse Persistence Tools
 */

public class TblphpbilDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblphpbilDAO.class);
	// property constants
	public static final String NO_AKAUN = "noAkaun";
	public static final String AMAUN_SEMASA = "amaunSemasa";
	public static final String AMAUN_PERLUDIBAYAR = "amaunPerludibayar";
	public static final String BAKI_AKHIR = "bakiAkhir";
	public static final String BULAN_BIL = "bulanBil";
	public static final String TAHUN_BIL = "tahunBil";
	public static final String FLAG_TANGGUH = "flagTangguh";
	public static final String FLAG_BAYAR = "flagBayar";
	public static final String FLAG_BATAL = "flagBatal";
	public static final String ID_BATAL = "idBatal";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String AMAUN_TERTUNGGAK = "amaunTertunggak";

	public void save(Tblphpbil transientInstance) {
		log.debug("saving Tblphpbil instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpbil persistentInstance) {
		log.debug("deleting Tblphpbil instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpbil findById(java.lang.Long id) {
		log.debug("getting Tblphpbil instance with id: " + id);
		try {
			Tblphpbil instance = (Tblphpbil) getSession().get(
					"ekptg.model.entities.Tblphpbil", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpbil instance) {
		log.debug("finding Tblphpbil instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpbil").add(
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
		log.debug("finding Tblphpbil instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblphpbil as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoAkaun(Object noAkaun) {
		return findByProperty(NO_AKAUN, noAkaun);
	}

	public List findByAmaunSemasa(Object amaunSemasa) {
		return findByProperty(AMAUN_SEMASA, amaunSemasa);
	}

	public List findByAmaunPerludibayar(Object amaunPerludibayar) {
		return findByProperty(AMAUN_PERLUDIBAYAR, amaunPerludibayar);
	}

	public List findByBakiAkhir(Object bakiAkhir) {
		return findByProperty(BAKI_AKHIR, bakiAkhir);
	}

	public List findByBulanBil(Object bulanBil) {
		return findByProperty(BULAN_BIL, bulanBil);
	}

	public List findByTahunBil(Object tahunBil) {
		return findByProperty(TAHUN_BIL, tahunBil);
	}

	public List findByFlagTangguh(Object flagTangguh) {
		return findByProperty(FLAG_TANGGUH, flagTangguh);
	}

	public List findByFlagBayar(Object flagBayar) {
		return findByProperty(FLAG_BAYAR, flagBayar);
	}

	public List findByFlagBatal(Object flagBatal) {
		return findByProperty(FLAG_BATAL, flagBatal);
	}

	public List findByIdBatal(Object idBatal) {
		return findByProperty(ID_BATAL, idBatal);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByAmaunTertunggak(Object amaunTertunggak) {
		return findByProperty(AMAUN_TERTUNGGAK, amaunTertunggak);
	}

	public List findAll() {
		log.debug("finding all Tblphpbil instances");
		try {
			String queryString = "from Tblphpbil";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpbil merge(Tblphpbil detachedInstance) {
		log.debug("merging Tblphpbil instance");
		try {
			Tblphpbil result = (Tblphpbil) getSession().merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpbil instance) {
		log.debug("attaching dirty Tblphpbil instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpbil instance) {
		log.debug("attaching clean Tblphpbil instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}