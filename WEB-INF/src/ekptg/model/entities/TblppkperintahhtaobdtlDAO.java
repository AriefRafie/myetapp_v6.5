package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkperintahhtaobdtl entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkperintahhtaobdtl
 * @author MyEclipse Persistence Tools
 */

public class TblppkperintahhtaobdtlDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkperintahhtaobdtlDAO.class);
	// property constants
	public static final String BA = "ba";
	public static final String BB = "bb";
	public static final String PEKALI = "pekali";
	public static final String MINOR = "minor";
	public static final String ID_PENJAGA1 = "idPenjaga1";
	public static final String KAVEAT1 = "kaveat1";
	public static final String BATAL_PA1 = "batalPa1";
	public static final String ID_PENJAGA2 = "idPenjaga2";
	public static final String KAVEAT2 = "kaveat2";
	public static final String BATAL_PA2 = "batalPa2";
	public static final String ID_PENJAGA3 = "idPenjaga3";
	public static final String KAVEAT3 = "kaveat3";
	public static final String BATAL_PA3 = "batalPa3";
	public static final String ID_PENJAGA4 = "idPenjaga4";
	public static final String KAVEAT4 = "kaveat4";
	public static final String BATAL_PA4 = "batalPa4";
	public static final String STATUS_TADBIR = "statusTadbir";
	public static final String CATATAN = "catatan";
	public static final String ID_PA1 = "idPa1";
	public static final String ID_PA2 = "idPa2";
	public static final String ID_PA3 = "idPa3";
	public static final String ID_PA4 = "idPa4";
	public static final String WAKIL = "wakil";
	public static final String KETERANGAN_OB = "keteranganOb";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkperintahhtaobdtl transientInstance) {
		log.debug("saving Tblppkperintahhtaobdtl instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkperintahhtaobdtl persistentInstance) {
		log.debug("deleting Tblppkperintahhtaobdtl instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkperintahhtaobdtl findById(java.lang.Long id) {
		log.debug("getting Tblppkperintahhtaobdtl instance with id: " + id);
		try {
			Tblppkperintahhtaobdtl instance = (Tblppkperintahhtaobdtl) getSession()
					.get("ekptg.model.entities.Tblppkperintahhtaobdtl", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkperintahhtaobdtl instance) {
		log.debug("finding Tblppkperintahhtaobdtl instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkperintahhtaobdtl").add(
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
		log.debug("finding Tblppkperintahhtaobdtl instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkperintahhtaobdtl as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBa(Object ba) {
		return findByProperty(BA, ba);
	}

	public List findByBb(Object bb) {
		return findByProperty(BB, bb);
	}

	public List findByPekali(Object pekali) {
		return findByProperty(PEKALI, pekali);
	}

	public List findByMinor(Object minor) {
		return findByProperty(MINOR, minor);
	}

	public List findByIdPenjaga1(Object idPenjaga1) {
		return findByProperty(ID_PENJAGA1, idPenjaga1);
	}

	public List findByKaveat1(Object kaveat1) {
		return findByProperty(KAVEAT1, kaveat1);
	}

	public List findByBatalPa1(Object batalPa1) {
		return findByProperty(BATAL_PA1, batalPa1);
	}

	public List findByIdPenjaga2(Object idPenjaga2) {
		return findByProperty(ID_PENJAGA2, idPenjaga2);
	}

	public List findByKaveat2(Object kaveat2) {
		return findByProperty(KAVEAT2, kaveat2);
	}

	public List findByBatalPa2(Object batalPa2) {
		return findByProperty(BATAL_PA2, batalPa2);
	}

	public List findByIdPenjaga3(Object idPenjaga3) {
		return findByProperty(ID_PENJAGA3, idPenjaga3);
	}

	public List findByKaveat3(Object kaveat3) {
		return findByProperty(KAVEAT3, kaveat3);
	}

	public List findByBatalPa3(Object batalPa3) {
		return findByProperty(BATAL_PA3, batalPa3);
	}

	public List findByIdPenjaga4(Object idPenjaga4) {
		return findByProperty(ID_PENJAGA4, idPenjaga4);
	}

	public List findByKaveat4(Object kaveat4) {
		return findByProperty(KAVEAT4, kaveat4);
	}

	public List findByBatalPa4(Object batalPa4) {
		return findByProperty(BATAL_PA4, batalPa4);
	}

	public List findByStatusTadbir(Object statusTadbir) {
		return findByProperty(STATUS_TADBIR, statusTadbir);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByIdPa1(Object idPa1) {
		return findByProperty(ID_PA1, idPa1);
	}

	public List findByIdPa2(Object idPa2) {
		return findByProperty(ID_PA2, idPa2);
	}

	public List findByIdPa3(Object idPa3) {
		return findByProperty(ID_PA3, idPa3);
	}

	public List findByIdPa4(Object idPa4) {
		return findByProperty(ID_PA4, idPa4);
	}

	public List findByWakil(Object wakil) {
		return findByProperty(WAKIL, wakil);
	}

	public List findByKeteranganOb(Object keteranganOb) {
		return findByProperty(KETERANGAN_OB, keteranganOb);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkperintahhtaobdtl instances");
		try {
			String queryString = "from Tblppkperintahhtaobdtl";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkperintahhtaobdtl merge(Tblppkperintahhtaobdtl detachedInstance) {
		log.debug("merging Tblppkperintahhtaobdtl instance");
		try {
			Tblppkperintahhtaobdtl result = (Tblppkperintahhtaobdtl) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkperintahhtaobdtl instance) {
		log.debug("attaching dirty Tblppkperintahhtaobdtl instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkperintahhtaobdtl instance) {
		log.debug("attaching clean Tblppkperintahhtaobdtl instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}