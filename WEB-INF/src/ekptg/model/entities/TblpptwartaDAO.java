package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptwarta entities. Transaction control of the save(), update() and
 * delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptwarta
 * @author MyEclipse Persistence Tools
 */

public class TblpptwartaDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory.getLog(TblpptwartaDAO.class);
	// property constants
	public static final String PROSES_WARTA = "prosesWarta";
	public static final String NO_WARTA = "noWarta";
	public static final String JENIS_WARTA = "jenisWarta";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_PENARIKANBALIK = "idPenarikanbalik";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptwarta transientInstance) {
		log.debug("saving Tblpptwarta instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptwarta persistentInstance) {
		log.debug("deleting Tblpptwarta instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptwarta findById(java.lang.Long id) {
		log.debug("getting Tblpptwarta instance with id: " + id);
		try {
			Tblpptwarta instance = (Tblpptwarta) getSession().get(
					"ekptg.model.entities.Tblpptwarta", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptwarta instance) {
		log.debug("finding Tblpptwarta instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptwarta").add(
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
		log.debug("finding Tblpptwarta instance with property: " + propertyName
				+ ", value: " + value);
		try {
			String queryString = "from Tblpptwarta as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByProsesWarta(Object prosesWarta) {
		return findByProperty(PROSES_WARTA, prosesWarta);
	}

	public List findByNoWarta(Object noWarta) {
		return findByProperty(NO_WARTA, noWarta);
	}

	public List findByJenisWarta(Object jenisWarta) {
		return findByProperty(JENIS_WARTA, jenisWarta);
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByIdPenarikanbalik(Object idPenarikanbalik) {
		return findByProperty(ID_PENARIKANBALIK, idPenarikanbalik);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdDb(Object idDb) {
		return findByProperty(ID_DB, idDb);
	}

	public List findAll() {
		log.debug("finding all Tblpptwarta instances");
		try {
			String queryString = "from Tblpptwarta";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptwarta merge(Tblpptwarta detachedInstance) {
		log.debug("merging Tblpptwarta instance");
		try {
			Tblpptwarta result = (Tblpptwarta) getSession().merge(
					detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptwarta instance) {
		log.debug("attaching dirty Tblpptwarta instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptwarta instance) {
		log.debug("attaching clean Tblpptwarta instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}