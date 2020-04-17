package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppthakmilikberikutpu entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppthakmilikberikutpu
 * @author MyEclipse Persistence Tools
 */

public class TblppthakmilikberikutpuDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppthakmilikberikutpuDAO.class);
	// property constants
	public static final String ID_SUBJAKET = "idSubjaket";
	public static final String ID_NEGERI = "idNegeri";
	public static final String ID_DAERAH = "idDaerah";
	public static final String ID_MUKIM = "idMukim";
	public static final String ID_JENISHAKMILIK = "idJenishakmilik";
	public static final String NO_HAKMILIK = "noHakmilik";
	public static final String NO_PU = "noPu";
	public static final String NO_PT = "noPt";
	public static final String NO_LOT = "noLot";
	public static final String ID_UNITLUASLOT = "idUnitluaslot";
	public static final String LUAS_LOT = "luasLot";
	public static final String ID_UNITLUASPT = "idUnitluaspt";
	public static final String LUAS_PT = "luasPt";
	public static final String ID_UNITLUASBARU = "idUnitluasbaru";
	public static final String LUAS_BARU = "luasBaru";
	public static final String FLAG_BATAL = "flagBatal";
	public static final String ULASAN = "ulasan";
	public static final String FLAG_PU = "flagPu";
	public static final String NO_BANGUNAN = "noBangunan";
	public static final String NO_TINGKAT = "noTingkat";
	public static final String NO_PETAK = "noPetak";
	public static final String ID_HAKMILIK = "idHakmilik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_BORANGK = "idBorangk";
	public static final String ID_DB = "idDb";

	public void save(Tblppthakmilikberikutpu transientInstance) {
		log.debug("saving Tblppthakmilikberikutpu instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppthakmilikberikutpu persistentInstance) {
		log.debug("deleting Tblppthakmilikberikutpu instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppthakmilikberikutpu findById(java.lang.Long id) {
		log.debug("getting Tblppthakmilikberikutpu instance with id: " + id);
		try {
			Tblppthakmilikberikutpu instance = (Tblppthakmilikberikutpu) getSession()
					.get("ekptg.model.entities.Tblppthakmilikberikutpu", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppthakmilikberikutpu instance) {
		log.debug("finding Tblppthakmilikberikutpu instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppthakmilikberikutpu").add(
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
		log.debug("finding Tblppthakmilikberikutpu instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppthakmilikberikutpu as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdSubjaket(Object idSubjaket) {
		return findByProperty(ID_SUBJAKET, idSubjaket);
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

	public List findByIdJenishakmilik(Object idJenishakmilik) {
		return findByProperty(ID_JENISHAKMILIK, idJenishakmilik);
	}

	public List findByNoHakmilik(Object noHakmilik) {
		return findByProperty(NO_HAKMILIK, noHakmilik);
	}

	public List findByNoPu(Object noPu) {
		return findByProperty(NO_PU, noPu);
	}

	public List findByNoPt(Object noPt) {
		return findByProperty(NO_PT, noPt);
	}

	public List findByNoLot(Object noLot) {
		return findByProperty(NO_LOT, noLot);
	}

	public List findByIdUnitluaslot(Object idUnitluaslot) {
		return findByProperty(ID_UNITLUASLOT, idUnitluaslot);
	}

	public List findByLuasLot(Object luasLot) {
		return findByProperty(LUAS_LOT, luasLot);
	}

	public List findByIdUnitluaspt(Object idUnitluaspt) {
		return findByProperty(ID_UNITLUASPT, idUnitluaspt);
	}

	public List findByLuasPt(Object luasPt) {
		return findByProperty(LUAS_PT, luasPt);
	}

	public List findByIdUnitluasbaru(Object idUnitluasbaru) {
		return findByProperty(ID_UNITLUASBARU, idUnitluasbaru);
	}

	public List findByLuasBaru(Object luasBaru) {
		return findByProperty(LUAS_BARU, luasBaru);
	}

	public List findByFlagBatal(Object flagBatal) {
		return findByProperty(FLAG_BATAL, flagBatal);
	}

	public List findByUlasan(Object ulasan) {
		return findByProperty(ULASAN, ulasan);
	}

	public List findByFlagPu(Object flagPu) {
		return findByProperty(FLAG_PU, flagPu);
	}

	public List findByNoBangunan(Object noBangunan) {
		return findByProperty(NO_BANGUNAN, noBangunan);
	}

	public List findByNoTingkat(Object noTingkat) {
		return findByProperty(NO_TINGKAT, noTingkat);
	}

	public List findByNoPetak(Object noPetak) {
		return findByProperty(NO_PETAK, noPetak);
	}

	public List findByIdHakmilik(Object idHakmilik) {
		return findByProperty(ID_HAKMILIK, idHakmilik);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdBorangk(Object idBorangk) {
		return findByProperty(ID_BORANGK, idBorangk);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblppthakmilikberikutpu instances");
		try {
			String queryString = "from Tblppthakmilikberikutpu";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppthakmilikberikutpu merge(
			Tblppthakmilikberikutpu detachedInstance) {
		log.debug("merging Tblppthakmilikberikutpu instance");
		try {
			Tblppthakmilikberikutpu result = (Tblppthakmilikberikutpu) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppthakmilikberikutpu instance) {
		log.debug("attaching dirty Tblppthakmilikberikutpu instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppthakmilikberikutpu instance) {
		log.debug("attaching clean Tblppthakmilikberikutpu instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}