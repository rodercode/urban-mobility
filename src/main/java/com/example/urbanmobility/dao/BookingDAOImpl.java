//package com.example.urbanmobility.dao;
//
//import com.example.urbanmobility.entity.Booking;
//import jakarta.persistence.EntityManager;
//import jakarta.transaction.Transactional;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Repository;
//
//@Repository
//public class BookingDAOImpl implements BookingDAO{
//
//    private EntityManager entityManager;
//
//    @Autowired
//    public BookingDAOImpl(EntityManager entityManager) {
//        this.entityManager = entityManager;
//    }
//
//    @Override
//    @Transactional
//    public void save(Booking booking) {
//        entityManager.persist(booking);
//    }
//}
