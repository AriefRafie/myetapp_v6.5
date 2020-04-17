package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphpbayaranperludibayar entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphpbayaranperludibayar
 * @author MyEclipse Persistence Tools
 */

public class TblphpbayaranperludibayarDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphpbayaranperludibayarDAO.class);
	// property constants
	public static final String AMAUN_PERLUDIBAYAR = "amaunPerludibayar";
	public static final String AMAUN_SEMASA = "amaunSemasa";
	public static final String AMAUN_TUNGGAKAN = "amaunTunggakan";
	public static final String BULAN_TUNGGAK_DARI = "bulanTunggakDari";
	public static final String TAHUN_TUNGGAK_DARI = "tahunTunggakDari";
	public static final String BULAN_TUNGGAK_HINGGA = "bulanTunggakHingga";
	public static final String TAHUN_TUNGGAK_HINGGA = "tahunTunggakHingga";
	public static final String BAKI_AWAL = "bakiAwal";
	public static final String BAKI_AKHIR = "bakiAkhir";
	public static final String ID_HASIL = "idHasil";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblphpbayaranperludibayar transientInstance) {
		log.debug("saving Tblphpbayaranperludibayar instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphpbayaranperludibayar persistentInstance) {
		log.debug("deleting Tblphpbayaranperludibayar instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphpbayaranperludibayar findById(java.lang.Long id) {
		log.debug("getting Tblphpbayaranperludibayar instance with id: " + id);
		try {
			Tblphpbayaranperludibayar instance = (Tblphpbayaranperludibayar) getSession()
					.get("ekptg.model.entities.Tblphpbayaranperludibayar", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphpbayaranperludibayar instance) {
		log.debug("finding Tblphpbayaranperludibayar instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphpbayaranperludibayar").add(
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
		log.debug("finding Tblphpbayaranperludibayar instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphpbayaranperludibayar as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByAmaunPerludibayar(Object amaunPerludibayar) {
		return findByProperty(AMAUN_PERLUDIBAYAR, amaunPerludibayar);
	}

	public List findByAmaunSemasa(Object amaunSemasa) {
		return findByProperty(AMAUN_SEMASA, amaunSemasa);
	}

	public List findByAmaunTunggakan(Object amaunTunggakan) {
		return findByProperty(AMAUN_TUNGGAKAN, amaunTunggakan);
	}

	public List findByBulanTunggakDari(Object bulanTunggakDari) {
		return findByProperty(BULAN_TUNGGAK_DARI, bulanTunggakDari);
	}

	public List findByTahunTunggakDari(Object tahunTunggakDari) {
		return findByProperty(TAHUN_TUNGGAK_DARI, tahunTunggakDari);
	}

	public List findByBulanTunggakHingga(Object bulanTunggakHingga) {
		return findByProperty(BULAN_TUNGGAK_HINGGA, bulanTunggakHingga);
	}

	public List findByTahunTunggakHingga(Object tahunTunggakHingga) {
		return findByProperty(TAHUN_TUNGGAK_HINGGA, tahunTunggakHingga);
	}

	public List findByBakiAwal(Object bakiAwal) {
		return findByProperty(BAKI_AWAL, bakiAwal);
	}

	public List findByBakiAkhir(Object bakiAkhir) {
		return findByProperty(BAKI_AKHIR, bakiAkhir);
	}

	public List findByIdHasil(Object idHasil) {
		return findByProperty(ID_HASIL, idHasil);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblphpbayaranperludibayar instances");
		try {
			String queryString = "from Tblphpbayaranperludibayar";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphpbayaranperludibayar merge(
			Tblphpbayaranperludibayar detachedInstance) {
		log.debug("merging Tblphpbayaranperludibayar instance");
		try {
			Tblphpbayaranperludibayar result = (Tblphpbayaranperludibayar) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphpbayaranperludibayar instance) {
		log.debug("attaching dirty Tblphpbayaranperludibayar instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphpbayaranperludibayar instance) {
		log.debug("attaching clean Tblphpbayaranperludibayar instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}