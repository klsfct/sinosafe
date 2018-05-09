package com.sinotn.examsafety.dao;

import java.io.Serializable;
import java.lang.reflect.ParameterizedType;
import java.lang.reflect.Type;
import java.util.List;

import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.criterion.Example;

import com.sinotn.common.exception.DaoException;


/**
 * 共享基础DAO，实现公共基�?��据操作，包括基本查询、保存�?更改、删除操�?�?��DAO类都继承此类
 * 
 * @author bai
 */
public class BaseObjectDAO<E, id extends Serializable> {
	/**
	 * 泛型对象
	 */
	public E e;
	/**
	 * 泛型class对象
	 */
	public Class<E> objClass;

	/**
	 * 注入hibernate核心管理�?
	 */
	private SessionFactory sessionFactory = null;

	/**
	 * 日志工具�?
	 */
	// private Logger log = null;

	/**
	 * 构�?函数，获取泛型对�?
	 */
	@SuppressWarnings("unchecked")
	public BaseObjectDAO() {
		this.objClass = null;
		Class<?> c = getClass();
		Type t = c.getGenericSuperclass();
		if (t instanceof ParameterizedType) {
			Type[] p = ((ParameterizedType) t).getActualTypeArguments();
			this.objClass = (Class<E>) p[0];
		}
	}

	/**
	 * 保存数据
	 * 
	 * @param obj
	 *            保存po对象
	 */
	public void save(E obj){
		// log = LoggerFactory.getLogger(obj.getClass());
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			session.save(obj);
			// log.debug(obj.getClass().getName() + " save successful!");
		} catch (HibernateException he) {
			// log.error("save failed", he);
			he.printStackTrace();
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 更新数据
	 * 
	 * @param obj
	 *            更新po对象
	 */
	public void update(E obj) {
		// log = LoggerFactory.getLogger(obj.getClass());
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			session.update(obj);
			// log.debug(obj.getClass().getName() + " update successful!");
		} catch (HibernateException he) {
			// log.error(obj.getClass().getName() + " update failed!", he);
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 保存或更新数�?
	 * 
	 * @param obj
	 *            保存或更新po对象
	 */
	public void saveOrUpdate(E obj) {
		// log = LoggerFactory.getLogger(obj.getClass());
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			session.clear();
			session.saveOrUpdate(obj);
			// log.debug(obj.getClass().getName() +
			// " saveOrUpdate successful!");
		} catch (HibernateException he) {
			// log.error(obj.getClass().getName() + " saveOrUpdate failed!",
			// he);
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 删除数据
	 * 
	 * @param obj
	 *            删除po对象
	 */
	public void delete(E obj) {
		// log = LoggerFactory.getLogger(obj.getClass());
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			session.delete(obj);
			// log.debug(obj.getClass().getName() + " delete successful!");
		} catch (HibernateException he) {
			// log.error(obj.getClass().getName() + " delete failed!", he);
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 根据指定的实体对象的class和主键�?获取实体对象
	 * 
	 * @param id
	 *            实体对象主键
	 */
	public Object findObjectById(Class<?> objClass, Serializable id) {
		Session session = this.getSessionFactory().getCurrentSession();
		try{
			Object object = session.get(objClass, id);
			return object;
		}
		catch(HibernateException he){
			throw new DaoException(he.getMessage());
		}
		
	}

	/**
	 * 根据本dao实现的实体对象的主键值获取到实体对象
	 * 
	 * @param id
	 *            实体对象主键
	 */
	@SuppressWarnings("unchecked")
	public E findObjectById(Serializable id) {
		Session session = this.getSessionFactory().getCurrentSession();
		try{
			Object object = session.get(objClass, id);
			return (E)object;
		}
		catch(HibernateException he){
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 根据po对象实例获取po对象数据列表
	 * 
	 * @param instance
	 *            po对象实例
	 * 
	 *            1.不支持主�?2.不支持关�?3.不支持NULL
	 */
	@SuppressWarnings("unchecked")
	public List<E> findByInstance(E instance) {
		// log = LoggerFactory.getLogger(instance.getClass());
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Example example = Example.create(instance);
			Criteria criteria = session.createCriteria(instance.getClass());
			criteria.add(example);
			List<E> results = criteria.list();
			// log.debug(instance.getClass().getName() +
			// " findByInstance successful!");
			return results;
		} catch (HibernateException he) {
			// log.error(instance.getClass().getName() +
			// " findByInstance failed!",he);
			throw new DaoException(he.getMessage());
		}
	}

	/**
	 * 根据实体对象属�?名称和�?获取实体对象列表
	 * 
	 * @param propertyName
	 *            属�?名称
	 * @param value
	 *            属�?�?
	 * @param sort
	 *            可�?参数，排序内�?
	 * @return 符合条件数据列表
	 */
	public List<E> findByProperty(String propertyName, Object value,
			String... sort) {
		return this.findByPropertyArry(new String[] { propertyName },
				new Object[] { value }, 0, sort);
	}

	/**
	 * 根据实体对象属�?名称和�?获取实体对象列表
	 * 
	 * @param propertyName
	 *            属�?名称
	 * @param value
	 *            属�?�?
	 * @return 符合条件数据列表
	 */
	public List<E> findByProperty(String propertyName, Object value) {
		return this.findByPropertyArry(new String[] { propertyName },
				new Object[] { value }, 0);
	}

	/**
	 * 根据属�?名称和�?获取指定数量的数据列表，
	 * 
	 * @param propertyName
	 *            属�?名称
	 * @param value
	 *            属�?�?
	 * @param size
	 *            列表数量
	 * @param sort
	 *            可�?参数，排序内�?
	 * @return 符合条件数据列表
	 */
	public List<E> findByProperty(String propertyName, Object value, int size,
			String... sort) {
		return this.findByPropertyArry(new String[] { propertyName },
				new Object[] { value }, size, sort);
	}

	/**
	 * 获取本类实现的实体对象前size条件数据列表
	 * 
	 * @param size
	 *            列表数量，如果size=0，标识查询结�?
	 * @param sort
	 *            可�?参数，排序内�?
	 * @return 符合条件数据列表
	 */
	public List<E> findByProperty(int size, String... sort) {
		return this.findByPropertyArry(null, null, size, sort);
	}

	/**
	 * 根据属�?名称和�?获取数据列表，不许保证属性名长度和�?的长度一�?
	 * 
	 * @param propertyName
	 *            属�?名称数组
	 * @param value
	 *            属�?值数�?
	 * @param sort
	 *            可�?参数，排序内�?
	 * @return 符合条件数据列表
	 */
	public List<E> findByPropertyArry(String[] propertyNames, Object[] values,
			String... sort) {
		return this.findByPropertyArry(propertyNames, values, 0, sort);
	}
	/**
	 * 根据属�?名称和�?获取数据列表，不许保证属性名长度和�?的长度一�?
	 * 
	 * @param propertyName
	 *            属�?名称数组
	 * @param value
	 *            属�?值数�?
	 * @return 符合条件数据列表
	 */
	public List<E> findByPropertyArry(String[] propertyNames, Object[] values) {
		return this.findByPropertyArry(propertyNames, values, 0);
	}

	/**
	 * 根据属�?名称和�?获取指定数量的数据列表，不许保证属�?名长度和值的长度�?��
	 * 
	 * @param propertyName
	 *            属�?名称数组
	 * @param value
	 *            属�?值数�?
	 * @param size
	 *            列表数量
	 * @param sort
	 *            可�?参数，排序内�?
	 * @return 符合条件数据列表
	 */
	@SuppressWarnings("unchecked")
	public List<E> findByPropertyArry(String[] propertyNames, Object[] values,
			int size, String... sort) {
		StringBuffer querySb = new StringBuffer();
		// --添加查询基础对象
		querySb.append(" from " + objClass.getName() + " as model ");
		// --判断是否有查询条�?
		if (propertyNames != null && propertyNames.length > 0) {
			int n = propertyNames.length;
			// --添加属�?查询条件
			querySb.append(" where ");
			for (int i = 0; i < n; i++) {
				if (i == 0) {
					querySb.append(" model." + propertyNames[i] + " = ? ");
				} else {
					querySb.append(" and model." + propertyNames[i] + " = ? ");
				}
			}
		}
		// --添加排序内容
		if (sort != null && sort.length > 0) {
			querySb.append(" order by ");
			for (int j = 0; j < sort.length; j++) {
				if (j == 0) {
					querySb.append(sort[j]);
				} else {
					querySb.append(" , " + sort[j]);
				}
			}
		}
		String hql = querySb.toString();
		List<E> retList = null;
		// log = LoggerFactory.getLogger(objClass.getName());
		try {
			Session session = this.getSessionFactory().getCurrentSession();
			Query query = session.createQuery(hql);
			if (size > 0) {
				query.setMaxResults(size);
			}
			if (propertyNames != null && propertyNames.length > 0) {
				for (int i = 0; i < propertyNames.length; i++) {
					query.setParameter(i, values[i]);
				}
			}
			retList = query.list();
			// log.debug(objClass.getName() + " findByInstance successful!");
		} catch (HibernateException he) {
			// log.error(objClass.getName() + " findByInstance failed!",he);
			throw new DaoException(he.getMessage());
		}
		return retList;
	}

	public void flush(){
		/*try {
			Session session = this.getSessionFactory().getCurrentSession();
			session.flush();
			// log.debug(objClass.getName() + " findByInstance successful!");
		} catch (HibernateException he) {
			// log.error(objClass.getName() + " findByInstance failed!",he);
			throw new DaoException(he.getMessage());
		}*/
	}
	public SessionFactory getSessionFactory() {
		return sessionFactory;
	}

	public void setSessionFactory(SessionFactory sessionFactory) {
		this.sessionFactory = sessionFactory;
	}

}