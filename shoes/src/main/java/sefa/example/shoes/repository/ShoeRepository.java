package sefa.example.shoes.repository;

import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import sefa.example.shoes.model.Shoe;


import java.util.List;
import java.util.Optional;

public interface ShoeRepository extends JpaRepository<Shoe,Integer> {



    Optional<List<Shoe>> findBySize(int size);

    Optional<List<Shoe>> findByColour(String colour);

    Optional<List<Shoe>> findByModel(String model);

    Optional<List<Shoe>> findByType(String type);
    Optional<List<Shoe>> findBySizeAndColour(int size,String colour);

    Optional<List<Shoe>> findByModelAndSize(String model,int size);

    Optional<List<Shoe>> findByModelAndColour(String model,String colour);


    Optional<List<Shoe>> findByModelAndColourAndSize(String model,String colour,int size);

   List<Shoe> findByDiscountRateGreaterThan(int discountRate);



   @Query("FROM Shoe ORDER BY amountOfSold ASC")
     List<Shoe> findAllOrderByAmountOfSoldAsc();

    @Query("FROM Shoe ORDER BY amountOfSold DESC ")
    List<Shoe> findAllOrderByAmountOfSoldDesc();











}
