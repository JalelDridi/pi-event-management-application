package com.esprit.pidev.resourcemodule.servicesImpl;

import com.esprit.pidev.resourcemodule.daos.ResourceDao;
import com.esprit.pidev.resourcemodule.daos.ResourceTypeDao;
import com.esprit.pidev.resourcemodule.dto.StatisticDto;
import com.esprit.pidev.resourcemodule.entities.ResourceType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class StatisticServiceImpl {
    @Autowired
    private ResourceTypeDao resourceTypeDao;

    @Autowired
    private ResourceDao resourceDao;

    public List<StatisticDto> getResourceTypeStatistics() {
        List<ResourceType> resourceTypes = resourceTypeDao.findAll();
        List<StatisticDto> statistics = new ArrayList<>();

        for (ResourceType resourceType : resourceTypes) {
            Long resourceCount = resourceDao.countByResourceType(resourceType);
            StatisticDto statisticDTO = new StatisticDto(resourceType.getResourceTypeName(), resourceCount);
            statistics.add(statisticDTO);
        }

        return statistics;
    }
}
