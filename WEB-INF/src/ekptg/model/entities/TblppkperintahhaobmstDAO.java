package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppkperintahhaobmst entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppkperintahhaobmst
 * @author MyEclipse Persistence Tools
 */

public class TblppkperintahhaobmstDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblppkperintahhaobmstDAO.class);
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

	public void save(Tblppkperintahhaobmst transientInstance) {
		log.debug("saving Tblppkperintahhaobmst instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppkperintahhaobmst persistentInstance) {
		log.debug("deleting Tblppkperintahhaobmst instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppkperintahhaobmst findById(java.lang.Long id) {
		log.debug("getting Tblppkperintahhaobmst instance with id: " + id);
		try {
			Tblppkperintahhaobmst instance = (Tblppkperintahhaobmst) getSession()
					.get("ekptg.model.entities.Tblppkperintahhaobmst", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppkperintahhaobmst instance) {
		log.debug("finding Tblppkperintahhaobmst instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppkperintahhaobmst").add(
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
		log.debug("finding Tblppkperintahhaobmst instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppkperintahhaobmst as model where model."
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
		log.debug("finding all Tblppkperintahhaobmst instances");
		try {
			String queryString = "from Tblppkperintahhaobmst";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppkperintahhaobmst merge(Tblppkperintahhaobmst detachedInstance) {
		log.debug("merging Tblppkperintahhaobmst instance");
		try {
			Tblppkperintahhaobmst result = (Tblppkperintahhaobmst) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppkperintahhaobmst instance) {
		log.debug("attaching dirty Tblppkperintahhaobmst instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppkperintahhaobmst instance) {
		log.debug("attaching clean Tblppkperintahhaobmst instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}