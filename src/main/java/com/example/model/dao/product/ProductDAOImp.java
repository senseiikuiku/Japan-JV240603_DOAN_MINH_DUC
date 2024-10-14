package com.example.model.dao.product;

import com.example.model.entity.Products;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class ProductDAOImp implements ProductDAO {
    @Autowired
    private SessionFactory sessionFactory;

    @Override
    public List<Products> findAll() {
        List<Products> productsList = new ArrayList<Products>();
        Session session = sessionFactory.openSession();
        try {
            productsList = session.createQuery("from Products ", Products.class).list();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return productsList;
    }

    @Override
    public boolean create(Products products) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.save(products);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public Products findById(int id) {
        Session session = sessionFactory.openSession();
        try {
            return session.get(Products.class, id);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            session.close();
        }
        return null;
    }

    @Override
    public boolean update(Products products) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.update(products);
            session.getTransaction().commit();
            return true;
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
            return false;
        } finally {
            session.close();
        }
    }

    @Override
    public void delete(int id) {
        Session session = sessionFactory.openSession();
        try {
            session.beginTransaction();
            session.delete(findById(id));
            session.getTransaction().commit();
        } catch (Exception e) {
            session.getTransaction().rollback();
            e.printStackTrace();
        } finally {
            session.close();
        }
    }
}
