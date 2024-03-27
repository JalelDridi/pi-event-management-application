package com.esprit.pidev.resourcemodule.controllers;

import com.esprit.pidev.resourcemodule.services.ResourceTypeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;

@Controller
public class ResourceTypeController {


    @Autowired
    private ResourceTypeService resourceTypeService;
}
