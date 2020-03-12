package se228.richard.ebookstore.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import se228.richard.ebookstore.entity.Order;

import java.util.List;

public interface OrderRepo extends JpaRepository<Order, Integer> {
    List<Order> findAllByUserid(int userid);
    List<Order> findAll();
}
