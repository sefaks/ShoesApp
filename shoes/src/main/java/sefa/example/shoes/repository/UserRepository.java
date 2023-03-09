package sefa.example.shoes.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import sefa.example.shoes.model.User;

public interface UserRepository extends JpaRepository<User,Integer> {



}
