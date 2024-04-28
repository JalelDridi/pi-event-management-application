//package com.esprit.pidev.resourcemodule.servicesImpl;
//
//import com.esprit.pidev.resourcemodule.entities.Resource;
//import com.esprit.pidev.resourcemodule.services.SearchService;
//import jakarta.persistence.EntityManager;
//import jakarta.persistence.PersistenceContext;
//import org.hibernate.search.jpa.FullTextEntityManager;
//import org.hibernate.search.jpa.Search;
//import org.hibernate.search.query.dsl.BooleanJunction;
//import org.hibernate.search.query.dsl.QueryBuilder;
//
//import org.springframework.stereotype.Service;
//
//import java.util.Date;
//import java.util.List;
//
//@Service
//public class SearchServiceImpl implements SearchService {
//    @PersistenceContext
//    private EntityManager entityManager;
//
//    public void fullIndexation() {
//        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager((javax.persistence.EntityManager) entityManager);
//        try {
//            fullTextEntityManager.createIndexer().startAndWait();
//        } catch (InterruptedException e) {
//            // Gérer l'exception
//        }
//    }
//
//
//
//    @Override
//    public List<Resource> findResourcesByAvailabilityAndDate(Date date) {
//        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager((javax.persistence.EntityManager) entityManager);
//        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Resource.class).get();
//
//        final BooleanJunction<?> boolJunction = queryBuilder.bool();
//        boolJunction.must(queryBuilder.keyword().onField("isAvailable").matching(true).createQuery());
//        boolJunction.must(queryBuilder.keyword().onField("date").matching(date).createQuery());
//
//        org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(boolJunction.createQuery(), Resource.class);
//        return (List<Resource>) jpaQuery.getResultList();
//    }
//
////    @Transactional
////    public List<Resource> findResourcesByAvailabilityAndDate(Date date) {
////        final FullTextEntityManager fullTextEntityManager = Search.getFullTextEntityManager((javax.persistence.EntityManager) entityManager);
////        final QueryBuilder queryBuilder = fullTextEntityManager.getSearchFactory().buildQueryBuilder().forEntity(Resource.class).get();
////
////        final BooleanJunction<?> boolJunction = queryBuilder.bool();
////        boolJunction.must(queryBuilder.keyword().onField("isAvailable").matching(true).createQuery());
////        boolJunction.must(queryBuilder.keyword().onField("date").matching(date).createQuery());
////
////        org.hibernate.search.jpa.FullTextQuery jpaQuery = fullTextEntityManager.createFullTextQuery(boolJunction.createQuery(), Resource.class);
////        return (List<Resource>) jpaQuery.getResultList();
////    }
//}
