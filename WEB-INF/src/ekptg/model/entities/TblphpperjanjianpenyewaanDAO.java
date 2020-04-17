package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpperjanjianpenyewaan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpperjanjianpenyewaan
 * @author MyEclipse Persistence Tools
 */

public class TblphpperjanjianpenyewaanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpperjanjianpenyewaanDAO.class);
	// property constants
	public static final String ID_FAILHASIL = "idFailhasil";
	public static final String NO_SIRI_PERJANJIAN = "noSiriPerjanjian";
	public static final String BIL_PERJANJIAN = "bilPerjanjian";
	public static final String ID_JENISTNH = "idJenistnh";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_JENISHM = "idJenishm";
	public static final String NO_HM = "noHm";
	public static final String ID_LOT = "idLot";
	public static final String NO_LOT = "noLot";
	public static final String NO_WARTA = "noWarta";
	public static final String ID_MENTERI = "idMenteri";
	public static final String ID_AGENSI = "idAgensi";
	public static final String KETERANGAN = "keterangan";
	public static final String ID_TEMPOH = "idTempoh";
	public static final String TEMPOH = "tempoh";
	public static final String KADAR_SEWA = "kadarSewa";
	public static final String ID_UNITLUAS = "idUnitluas";
	public static final String LUAS = "luas";
	public static final String STATUS_FAIL = "statusFail";
	public static final String DEPOSIT = "deposit";
	public static final String KADAR_BAYARAN = "kadarBayaran";
	public static final String ID_JENISSEWA = "idJenissewa";
	public static final String STATUS_PERJANJIAN = "statusPerjanjian";
	public static final String FLAG_GUNA = "flagGuna";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpperjanjianpenyewaan transientInstance) {
		log.debug("saving Tblphpperjanjianpenyewaan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpperjanjianpenyewaan persistentInstance) {
		log.debug("deleting Tblphpperjanjianpenyewaan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpperjanjianpenyewaan findById(java.lang.Long id) {
		log.debug("getting Tblphpperjanjianpenyewaan instance with id: " + id);
		try {
			Tblphpperjanjianpenyewaan instance = (Tblphpperjanjianpenyewaan) getSession()
					.get("ekptg.model.entities.Tblphpperjanjianpenyewaan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpperjanjianpenyewaan instance) {
		log.debug("finding Tblphpperjanjianpenyewaan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpperjanjianpenyewaan").add(
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
		log.debug("finding Tblphpperjanjianpenyewaan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpperjanjianpenyewaan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdFailhasil(Object idFailhasil) {
		return findByProperty(ID_FAILHASIL, idFailhasil);
	}

	public List findByNoSiriPerjanjian(Object noSiriPerjanjian) {
		return findByProperty(NO_SIRI_PERJANJIAN, noSiriPerjanjian);
	}

	public List findByBilPerjanjian(Object bilPerjanjian) {
		return findByProperty(BIL_PERJANJIAN, bilPerjanjian);
	}

	public List findByIdJenistnh(Object idJenistnh) {
		return findByProperty(ID_JENISTNH, idJenistnh);
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByIdNegeri(Object idNegeri) {
		return findByProperty(ID_NEGERI, idNegeri);
	}

	public List findByIdDaerah(Object idDaerah) {
		return findByProperty(ID_DAERAH, idDaerah);
	}

	public List findByIdMukim(Object idMukim) {
		return findByProperty(ID_MUKIM, idMukim);
	}

	public List findByIdJenishm(Object idJenishm) {
		return findByProperty(ID_JENISHM, idJenishm);
	}

	public List findByNoHm(Object noHm) {
		return findByProperty(NO_HM, noHm);
	}

	public List findByIdLot(Object idLot) {
		return findByProperty(ID_LOT, idLot);
	}

	public List findByNoLot(Object noLot) {
		return findByProperty(NO_LOT, noLot);
	}

	public List findByNoWarta(Object noWarta) {
		return findByProperty(NO_WARTA, noWarta);
	}

	public List findByIdMenteri(Object idMenteri) {
		return findByProperty(ID_MENTERI, idMenteri);
	}

	public List findByIdAgensi(Object idAgensi) {
		return findByProperty(ID_AGENSI, idAgensi);
	}

	public List findByKeterangan(Object keterangan) {
		return findByProperty(KETERANGAN, keterangan);
	}

	public List findByIdTempoh(Object idTempoh) {
		return findByProperty(ID_TEMPOH, idTempoh);
	}

	public List findByTempoh(Object tempoh) {
		return findByProperty(TEMPOH, tempoh);
	}

	public List findByKadarSewa(Object kadarSewa) {
		return findByProperty(KADAR_SEWA, kadarSewa);
	}

	public List findByIdUnitluas(Object idUnitluas) {
		return findByProperty(ID_UNITLUAS, idUnitluas);
	}

	public List findByLuas(Object luas) {
		return findByProperty(LUAS, luas);
	}

	public List findByStatusFail(Object statusFail) {
		return findByProperty(STATUS_FAIL, statusFail);
	}

	public List findByDeposit(Object deposit) {
		return findByProperty(DEPOSIT, deposit);
	}

	public List findByKadarBayaran(Object kadarBayaran) {
		return findByProperty(KADAR_BAYARAN, kadarBayaran);
	}

	public List findByIdJenissewa(Object idJenissewa) {
		return findByProperty(ID_JENISSEWA, idJenissewa);
	}

	public List findByStatusPerjanjian(Object statusPerjanjian) {
		return findByProperty(STATUS_PERJANJIAN, statusPerjanjian);
	}

	public List findByFlagGuna(Object flagGuna) {
		return findByProperty(FLAG_GUNA, flagGuna);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpperjanjianpenyewaan instances");
		try {
			String queryString = "from Tblphpperjanjianpenyewaan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpperjanjianpenyewaan merge(
			Tblphpperjanjianpenyewaan detachedInstance) {
		log.debug("merging Tblphpperjanjianpenyewaan instance");
		try {
			Tblphpperjanjianpenyewaan result = (Tblphpperjanjianpenyewaan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpperjanjianpenyewaan instance) {
		log.debug("attaching dirty Tblphpperjanjianpenyewaan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpperjanjianpenyewaan instance) {
		log.debug("attaching clean Tblphpperjanjianpenyewaan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}