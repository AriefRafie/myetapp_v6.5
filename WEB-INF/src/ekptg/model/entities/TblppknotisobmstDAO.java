package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblppknotisobmst entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblppknotisobmst
 * @author MyEclipse Persistence Tools
 */

public class TblppknotisobmstDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblppknotisobmstDAO.class);
	// property constants
	public static final String BIL = "bil";
	public static final String STATUS_SERAH = "statusSerah";
	public static final String JENIS_SERAH = "jenisSerah";
	public static final String STATUS_AKUAN_SUMPAH = "statusAkuanSumpah";
	public static final String CATATAN = "catatan";
	public static final String NAMA_PENGHANTAR_NOTIS = "namaPenghantarNotis";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";

	public void save(Tblppknotisobmst transientInstance) {
		log.debug("saving Tblppknotisobmst instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblppknotisobmst persistentInstance) {
		log.debug("deleting Tblppknotisobmst instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblppknotisobmst findById(java.lang.Long id) {
		log.debug("getting Tblppknotisobmst instance with id: " + id);
		try {
			Tblppknotisobmst instance = (Tblppknotisobmst) getSession().get(
					"ekptg.model.entities.Tblppknotisobmst", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblppknotisobmst instance) {
		log.debug("finding Tblppknotisobmst instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblppknotisobmst").add(
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
		log.debug("finding Tblppknotisobmst instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblppknotisobmst as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByBil(Object bil) {
		return findByProperty(BIL, bil);
	}

	public List findByStatusSerah(Object statusSerah) {
		return findByProperty(STATUS_SERAH, statusSerah);
	}

	public List findByJenisSerah(Object jenisSerah) {
		return findByProperty(JENIS_SERAH, jenisSerah);
	}

	public List findByStatusAkuanSumpah(Object statusAkuanSumpah) {
		return findByProperty(STATUS_AKUAN_SUMPAH, statusAkuanSumpah);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByNamaPenghantarNotis(Object namaPenghantarNotis) {
		return findByProperty(NAMA_PENGHANTAR_NOTIS, namaPenghantarNotis);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findAll() {
		log.debug("finding all Tblppknotisobmst instances");
		try {
			String queryString = "from Tblppknotisobmst";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblppknotisobmst merge(Tblppknotisobmst detachedInstance) {
		log.debug("merging Tblppknotisobmst instance");
		try {
			Tblppknotisobmst result = (Tblppknotisobmst) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblppknotisobmst instance) {
		log.debug("attaching dirty Tblppknotisobmst instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblppknotisobmst instance) {
		log.debug("attaching clean Tblppknotisobmst instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}