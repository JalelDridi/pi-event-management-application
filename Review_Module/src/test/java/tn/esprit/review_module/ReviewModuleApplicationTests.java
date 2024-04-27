package tn.esprit.review_module;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import tn.esprit.review_module.entities.Review;
import tn.esprit.review_module.servicesImpl.ReviewServiceImpl;

@SpringBootTest
class ReviewModuleApplicationTests {

    @Autowired
    private ReviewServiceImpl reviewService;
    @Test
    void contextLoads() {
    }

}
