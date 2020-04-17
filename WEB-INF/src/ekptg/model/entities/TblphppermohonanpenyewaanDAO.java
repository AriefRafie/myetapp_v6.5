package ekptg.model.entities;

import java.util.List;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.hibernate.LockMode;
import org.hibernate.Query;
import org.hibernate.criterion.Example;

/**
 * A data access object (DAO) providing persistence and search support for
 * Tblphppermohonanpenyewaan entities. Transaction control of the save(),
 * update() and delete() operations can directly support Spring
 * container-managed transactions or they can be augmented to handle
 * user-managed Spring transactions. Each of these methods provides additional
 * information for how to configure it for the desired type of transaction
 * control.
 * 
 * @see ekptg.model.entities.Tblphppermohonanpenyewaan
 * @author MyEclipse Persistence Tools
 */

public class TblphppermohonanpenyewaanDAO extends BaseHibernateDAO {
	private static final Log log = LogFactory
			.getLog(TblphppermohonanpenyewaanDAO.class);
	// property constants
	public static final String ID_FAIL = "idFail";
	public static final String TUJUAN = "tujuan";
	public static final String ID_JENISSEWA = "idJenissewa";
	public static final String KETERANGAN_ADUAN = "keteranganAduan";
	public static final String LOKASI = "lokasi";
	public static final String TAJUK_PERMOHONAN = "tajukPermohonan";
	public static final String ID_UNITLUASMHN = "idUnitluasmhn";
	public static final String LUAS_MHN = "luasMhn";
	public static final String ID_UNITLUASBAKI = "idUnitluasbaki";
	public static final String LUAS_BAKI = "luasBaki";
	public static final String FLAG_GUNA = "flagGuna";
	public static final String ID_STATUS = "idStatus";
	public static final String NO_PERMOHONAN_ONLINE = "noPermohonanOnline";
	public static final String FLAG_LBH_CEROBOH = "flagLbhCeroboh";
	public static final String ID_JENISCEROBOH = "idJenisceroboh";
	public static final String ID_MASUK = "idMasuk";
	public static final String ID_KEMASKINI = "idKemaskini";
	public static final String ID_URUSAN = "idUrusan";

	public void save(Tblphppermohonanpenyewaan transientInstance) {
		log.debug("saving Tblphppermohonanpenyewaan instance");
		try {
			getSession().save(transientInstance);
			log.debug("save successful");
		} catch (RuntimeException re) {
			log.error("save failed", re);
			throw re;
		}
	}

	public void delete(Tblphppermohonanpenyewaan persistentInstance) {
		log.debug("deleting Tblphppermohonanpenyewaan instance");
		try {
			getSession().delete(persistentInstance);
			log.debug("delete successful");
		} catch (RuntimeException re) {
			log.error("delete failed", re);
			throw re;
		}
	}

	public Tblphppermohonanpenyewaan findById(java.lang.Long id) {
		log.debug("getting Tblphppermohonanpenyewaan instance with id: " + id);
		try {
			Tblphppermohonanpenyewaan instance = (Tblphppermohonanpenyewaan) getSession()
					.get("ekptg.model.entities.Tblphppermohonanpenyewaan", id);
			return instance;
		} catch (RuntimeException re) {
			log.error("get failed", re);
			throw re;
		}
	}

	public List findByExample(Tblphppermohonanpenyewaan instance) {
		log.debug("finding Tblphppermohonanpenyewaan instance by example");
		try {
			List results = getSession().createCriteria(
					"ekptg.model.entities.Tblphppermohonanpenyewaan").add(
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
		log.debug("finding Tblphppermohonanpenyewaan instance with property: "
				+ propertyName + ", value: " + value);
		try {
			String queryString = "from Tblphppermohonanpenyewaan as model where model."
					+ propertyName + "= ?";
			Query queryObject = getSession().createQuery(queryString);
			queryObject.setParameter(0, value);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find by property name failed", re);
			throw re;
		}
	}

	public List findByIdFail(Object idFail) {
		return findByProperty(ID_FAIL, idFail);
	}

	public List findByTujuan(Object tujuan) {
		return findByProperty(TUJUAN, tujuan);
	}

	public List findByIdJenissewa(Object idJenissewa) {
		return findByProperty(ID_JENISSEWA, idJenissewa);
	}

	public List findByKeteranganAduan(Object keteranganAduan) {
		return findByProperty(KETERANGAN_ADUAN, keteranganAduan);
	}

	public List findByLokasi(Object lokasi) {
		return findByProperty(LOKASI, lokasi);
	}

	public List findByTajukPermohonan(Object tajukPermohonan) {
		return findByProperty(TAJUK_PERMOHONAN, tajukPermohonan);
	}

	public List findByIdUnitluasmhn(Object idUnitluasmhn) {
		return findByProperty(ID_UNITLUASMHN, idUnitluasmhn);
	}

	public List findByLuasMhn(Object luasMhn) {
		return findByProperty(LUAS_MHN, luasMhn);
	}

	public List findByIdUnitluasbaki(Object idUnitluasbaki) {
		return findByProperty(ID_UNITLUASBAKI, idUnitluasbaki);
	}

	public List findByLuasBaki(Object luasBaki) {
		return findByProperty(LUAS_BAKI, luasBaki);
	}

	public List findByFlagGuna(Object flagGuna) {
		return findByProperty(FLAG_GUNA, flagGuna);
	}

	public List findByIdStatus(Object idStatus) {
		return findByProperty(ID_STATUS, idStatus);
	}

	public List findByNoPermohonanOnline(Object noPermohonanOnline) {
		return findByProperty(NO_PERMOHONAN_ONLINE, noPermohonanOnline);
	}

	public List findByFlagLbhCeroboh(Object flagLbhCeroboh) {
		return findByProperty(FLAG_LBH_CEROBOH, flagLbhCeroboh);
	}

	public List findByIdJenisceroboh(Object idJenisceroboh) {
		return findByProperty(ID_JENISCEROBOH, idJenisceroboh);
	}

	public List findByIdMasuk(Object idMasuk) {
		return findByProperty(ID_MASUK, idMasuk);
	}

	public List findByIdKemaskini(Object idKemaskini) {
		return findByProperty(ID_KEMASKINI, idKemaskini);
	}

	public List findByIdUrusan(Object idUrusan) {
		return findByProperty(ID_URUSAN, idUrusan);
	}

	public List findAll() {
		log.debug("finding all Tblphppermohonanpenyewaan instances");
		try {
			String queryString = "from Tblphppermohonanpenyewaan";
			Query queryObject = getSession().createQuery(queryString);
			return queryObject.list();
		} catch (RuntimeException re) {
			log.error("find all failed", re);
			throw re;
		}
	}

	public Tblphppermohonanpenyewaan merge(
			Tblphppermohonanpenyewaan detachedInstance) {
		log.debug("merging Tblphppermohonanpenyewaan instance");
		try {
			Tblphppermohonanpenyewaan result = (Tblphppermohonanpenyewaan) getSession()
					.merge(detachedInstance);
			log.debug("merge successful");
			return result;
		} catch (RuntimeException re) {
			log.error("merge failed", re);
			throw re;
		}
	}

	public void attachDirty(Tblphppermohonanpenyewaan instance) {
		log.debug("attaching dirty Tblphppermohonanpenyewaan instance");
		try {
			getSession().saveOrUpdate(instance);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}

	public void attachClean(Tblphppermohonanpenyewaan instance) {
		log.debug("attaching clean Tblphppermohonanpenyewaan instance");
		try {
			getSession().lock(instance, LockMode.NONE);
			log.debug("attach successful");
		} catch (RuntimeException re) {
			log.error("attach failed", re);
			throw re;
		}
	}
}