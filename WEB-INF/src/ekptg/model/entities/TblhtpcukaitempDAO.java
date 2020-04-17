package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblhtpcukaitemp entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblhtpcukaitemp
 * @author MyEclipse Persistence Tools
 */

public class TblhtpcukaitempDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblhtpcukaitempDAO.class);
	// property constants
	public static final String TUNGGAKAN = "tunggakan";
	public static final String TAHUN = "tahun";
	public static final String CUKAI_KENA_BAYAR = "cukaiKenaBayar";
	public static final String CUKAI_PERLU_BAYAR = "cukaiPerluBayar";
	public static final String CUKAI_DIBAYAR = "cukaiDibayar";
	public static final String DENDA = "denda";
	public static final String BAYARAN_LAIN = "bayaranLain";
	public static final String CUKAI_TALIAIR = "cukaiTaliair";
	public static final String CUKAI_PARIT = "cukaiParit";
	public static final String PENGURANGAN = "pengurangan";
	public static final String PENGECUALIAN = "pengecualian";
	public static final String NO_RESIT = "noResit";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblhtpcukaitemp transientInstance) {
		log.debug("saving Tblhtpcukaitemp instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblhtpcukaitemp persistentInstance) {
		log.debug("deleting Tblhtpcukaitemp instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblhtpcukaitemp findById(java.lang.Long id) {
		log.debug("getting Tblhtpcukaitemp instance with id: " + id);
		try {
			Tblhtpcukaitemp instance = (Tblhtpcukaitemp) getSession().get(
					"ekptg.model.entities.Tblhtpcukaitemp", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblhtpcukaitemp instance) {
		log.debug("finding Tblhtpcukaitemp instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblhtpcukaitemp").add(
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
		log.debug("finding Tblhtpcukaitemp instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblhtpcukaitemp as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByTunggakan(Object tunggakan) {
		return findByProperty(TUNGGAKAN, tunggakan);
	}

	public List findByTahun(Object tahun) {
		return findByProperty(TAHUN, tahun);
	}

	public List findByCukaiKenaBayar(Object cukaiKenaBayar) {
		return findByProperty(CUKAI_KENA_BAYAR, cukaiKenaBayar);
	}

	public List findByCukaiPerluBayar(Object cukaiPerluBayar) {
		return findByProperty(CUKAI_PERLU_BAYAR, cukaiPerluBayar);
	}

	public List findByCukaiDibayar(Object cukaiDibayar) {
		return findByProperty(CUKAI_DIBAYAR, cukaiDibayar);
	}

	public List findByDenda(Object denda) {
		return findByProperty(DENDA, denda);
	}

	public List findByBayaranLain(Object bayaranLain) {
		return findByProperty(BAYARAN_LAIN, bayaranLain);
	}

	public List findByCukaiTaliair(Object cukaiTaliair) {
		return findByProperty(CUKAI_TALIAIR, cukaiTaliair);
	}

	public List findByCukaiParit(Object cukaiParit) {
		return findByProperty(CUKAI_PARIT, cukaiParit);
	}

	public List findByPengurangan(Object pengurangan) {
		return findByProperty(PENGURANGAN, pengurangan);
	}

	public List findByPengecualian(Object pengecualian) {
		return findByProperty(PENGECUALIAN, pengecualian);
	}

	public List findByNoResit(Object noResit) {
		return findByProperty(NO_RESIT, noResit);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdMukim(Object idMukim) {
		return findByProperty(ID_MUKIM, idMukim);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblhtpcukaitemp instances");
		try {
			String queryString = "from Tblhtpcukaitemp";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblhtpcukaitemp merge(Tblhtpcukaitemp detachedInstance) {
		log.debug("merging Tblhtpcukaitemp instance");
		try {
			Tblhtpcukaitemp result = (Tblhtpcukaitemp) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblhtpcukaitemp instance) {
		log.debug("attaching dirty Tblhtpcukaitemp instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblhtpcukaitemp instance) {
		log.debug("attaching clean Tblhtpcukaitemp instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}