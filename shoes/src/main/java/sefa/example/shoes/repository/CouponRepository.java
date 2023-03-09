package sefa.example.shoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import sefa.example.shoes.model.Coupon;

import java.util.List;
import java.util.Optional;

public interface CouponRepository extends JpaRepository<Coupon,Long> {

    List<Coupon> findByCouponId(int couponId);

    Coupon findById(int id);
}