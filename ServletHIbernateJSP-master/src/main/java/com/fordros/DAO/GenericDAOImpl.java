package com.fordros.DAO;

import java.io.Serializable;
import java.util.List;

import com.fordros.persistence.HibernateUtil;
import org.hibernate.Query;
import org.hibernate.Session;
/**
 * Created by Fordros on 22.03.2016.
 */

    public abstract class GenericDAOImpl<T, ID extends Serializable> implements GenericDAO<T, ID> {

        protected Session getSession() {
            return HibernateUtil.getSession();
        }

        public void save(T entity) {
            Session hibernateSession = this.getSession();
            hibernateSession.saveOrUpdate(entity);
        }

        public void merge(T entity) {
            Session hibernateSession = this.getSession();
            hibernateSession.merge(entity);
        }

        public void delete(T entity) {
            Session hibernateSession = this.getSession();
            hibernateSession.delete(entity);
        }

        public List<T> findMany(Query query) {
            List<T> t;
            t = (List<T>) query.list();
            return t;
        }

        public T findOne(Query query) {
            T t;
            t = (T) query.uniqueResult();
            return t;
        }

        public T findByID(Class clazz, Integer id) {
            Session hibernateSession = this.getSession();
            T t = null;
            t = (T) hibernateSession.get(clazz, id);
            return t;
        }

        public List findAll(Class clazz) {
            Session hibernateSession = this.getSession();
            List T = null;
            Query query = hibernateSession.createQuery("from " + clazz.getName());
            T = query.list();
            return T;
        }
}
