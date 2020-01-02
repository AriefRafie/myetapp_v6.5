package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblpptbuktipenyampaian entities. Transaction control of the save(), update()
 * and delete() operations can directly support Spring container-managed
 * transactions or they can be augmented to handle user-managed Spring
 * transactions. Each of these methods provides additional information for how
 * to configure it for the desired type of transaction control.
 * 
 * @see ekptg.model.entities.Tblpptbuktipenyampaian
 * @author MyEclipse Persistence Tools
 */

public class TblpptbuktipenyampaianDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblpptbuktipenyampaianDAO.class);
	// property constants
	public static final String NO_DOKUMEN = "noDokumen";
	public static final String ID_JENISDOKUMEN = "idJenisdokumen";
	public static final String FLAG_SERAH = "flagSerah	";
	public static final String CATATAN = "catatan";
	public static final String JENIS_SERAHAN = "jenisSerahan";
	public static final String NAMA_PENERIMA = "namaPenerima";
	public static final String NO_KP_PENERIMA = "noKpPenerima";
	public static final String ID_PERMOHONAN = "idPermohonan";
	public static final String ID_BANTAHAN = "idBantahan	";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_DB = "idDb";

	public void save(Tblpptbuktipenyampaian transientInstance) {
		log.debug("saving Tblpptbuktipenyampaian instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblpptbuktipenyampaian persistentInstance) {
		log.debug("deleting Tblpptbuktipenyampaian instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblpptbuktipenyampaian findById(java.lang.Long id) {
		log.debug("getting Tblpptbuktipenyampaian instance with id: " + id);
		try {
			Tblpptbuktipenyampaian instance = (Tblpptbuktipenyampaian) getSession()
					.get("ekptg.model.entities.Tblpptbuktipenyampaian", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblpptbuktipenyampaian instance) {
		log.debug("finding Tblpptbuktipenyampaian instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblpptbuktipenyampaian").add(
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
		log.debug("finding Tblpptbuktipenyampaian instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblpptbuktipenyampaian as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByNoDokumen(Object noDokumen) {
		return findByProperty(NO_DOKUMEN, noDokumen);
	}

	public List findByIdJenisdokumen(Object idJenisdokumen) {
		return findByProperty(ID_JENISDOKUMEN, idJenisdokumen);
	}

	public List findByFlagSerah(Object flagSerah) {
		return findByProperty(FLAG_SERAH, flagSerah);
	}

	public List findByCatatan(Object catatan) {
		return findByProperty(CATATAN, catatan);
	}

	public List findByJenisSerahan(Object jenisSerahan) {
		return findByProperty(JENIS_SERAHAN, jenisSerahan);
	}

	public List findByNamaPenerima(Object namaPenerima) {
		return findByProperty(NAMA_PENERIMA, namaPenerima);
	}

	public List findByNoKpPenerima(Object noKpPenerima) {
		return findByProperty(NO_KP_PENERIMA, noKpPenerima);
	}

	public List findByIdPermohonan(Object idPermohonan) {
		return findByProperty(ID_PERMOHONAN, idPermohonan);
	}

	public List findByIdBantahan(Object idBantahan) {
		return findByProperty(ID_BANTAHAN, idBantahan);
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
		log.debug("finding all Tblpptbuktipenyampaian instances");
		try {
			String queryString = "from Tblpptbuktipenyampaian";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblpptbuktipenyampaian merge(Tblpptbuktipenyampaian detachedInstance) {
		log.debug("merging Tblpptbuktipenyampaian instance");
		try {
			Tblpptbuktipenyampaian result = (Tblpptbuktipenyampaian) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblpptbuktipenyampaian instance) {
		log.debug("attaching dirty Tblpptbuktipenyampaian instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblpptbuktipenyampaian instance) {
		log.debug("attaching clean Tblpptbuktipenyampaian instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}