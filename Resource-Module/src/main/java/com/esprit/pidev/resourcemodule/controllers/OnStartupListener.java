//package com.esprit.pidev.resourcemodule.controllers;
//
//import com.esprit.pidev.resourcemodule.entities.Resource;
//import com.esprit.pidev.resourcemodule.services.SearchService;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.context.ApplicationListener;
//import org.springframework.context.event.ContextRefreshedEvent;
//import org.springframework.stereotype.Component;
//import org.springframework.web.bind.annotation.CrossOrigin;
//
//import java.util.Date;
//import java.util.List;
//
//@Component
//public class OnStartupListener  implements ApplicationListener<ContextRefreshedEvent> {
//    @Autowired
//    private SearchService searchService;
//
//    @Override
//    public void onApplicationEvent(ContextRefreshedEvent event) {
//        searchService.fullIndexation();
//    }
//
//}
