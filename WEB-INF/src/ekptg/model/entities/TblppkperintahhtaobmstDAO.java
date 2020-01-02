package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkperintahhtaobmst entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkperintahhtaobmst
 * @author MyEclipse Persistence Tools
 */

public class TblppkperintahhtaobmstDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkperintahhtaobmstDAO.class);
	// property constants
	public static final String CATATAN = "catatan";
	public static final String NILAI_BERSIH = "nilaiBersih";
	public static final String CUKAI_HARTA = "cukaiHarta";
	public static final String NAMA_PEMBAYAR_CUKAI = "namaPembayarCukai";
	public static final String AMAUN = "amaun";
	public static final String JENIS_LELONG = "jenisLelong";
	public static final String HARGA_RIZAB = "hargaRizab";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppkperintahhtaobmst transientInstance) {
		log.debug("saving Tblppkperintahhtaobmst instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkperintahhtaobmst persistentInstance) {
		log.debug("deleting Tblppkperintahhtaobmst instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkperintahhtaobmst findById(java.lang.Long id) {
		log.debug("getting Tblppkperintahhtaobmst instance with id: " + id);
		try {
			Tblppkperintahhtaobmst instance = (Tblppkperintahhtaobmst) getSession()
					.get("ekptg.model.entities.Tblppkperintahhtaobmst", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkperintahhtaobmst instance) {
		log.debug("finding Tblppkperintahhtaobmst instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkperintahhtaobmst").add(
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
		log.debug("finding Tblppkperintahhtaobmst instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkperintahhtaobmst as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByNilaiBersih(Object nilaiBersih) {
		return findByProperty(NILAI_BERSIH, nilaiBersih);
	}

	public List findByCukaiHarta(Object cukaiHarta) {
		return findByProperty(CUKAI_HARTA, cukaiHarta);
	}

	public List findByNamaPembayarCukai(Object namaPembayarCukai) {
		return findByProperty(NAMA_PEMBAYAR_CUKAI, namaPembayarCukai);
	}

	public List findByAmaun(Object amaun) {
		return findByProperty(AMAUN, amaun);
	}

	public List findByJenisLelong(Object jenisLelong) {
		return findByProperty(JENIS_LELONG, jenisLelong);
	}

	public List findByHargaRizab(Object hargaRizab) {
		return findByProperty(HARGA_RIZAB, hargaRizab);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppkperintahhtaobmst instances");
		try {
			String queryString = "from Tblppkperintahhtaobmst";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkperintahhtaobmst merge(Tblppkperintahhtaobmst detachedInstance) {
		log.debug("merging Tblppkperintahhtaobmst instance");
		try {
			Tblppkperintahhtaobmst result = (Tblppkperintahhtaobmst) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkperintahhtaobmst instance) {
		log.debug("attaching dirty Tblppkperintahhtaobmst instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkperintahhtaobmst instance) {
		log.debug("attaching clean Tblppkperintahhtaobmst instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}