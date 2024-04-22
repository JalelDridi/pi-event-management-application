package tn.esprit.review_module.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import tn.esprit.review_module.dtos.EventDto;

public interface EventRepository extends JpaRepository<EventDto,Long> {
  EventDto findEventDtoById(Long id);

}
