package ua.org.javaday.spring.mvc.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.org.javaday.spring.mvc.domain.Channel;

public interface ChannelRepository extends JpaRepository<Channel, Integer> {
}
