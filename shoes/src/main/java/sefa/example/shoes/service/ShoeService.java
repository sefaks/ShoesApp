package sefa.example.shoes.service;


import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import sefa.example.shoes.exception.ShoesNotFoundException;

import sefa.example.shoes.model.Shoe;
import sefa.example.shoes.model.User;
import sefa.example.shoes.repository.ShoeRepository;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import static java.util.Collections.*;

@Service
@RequiredArgsConstructor
public class ShoeService {

    private final ShoeRepository shoeRepository;


    public Shoe saveShoe (Shoe shoe) {


        Shoe shoe1 = new Shoe();
        shoe1.setSize(shoe.getSize());
        shoe1.setColour(shoe.getColour());
        shoe1.setStock(shoe.getStock());
        shoe1.setPrice(shoe.getPrice());
        shoe1.setModel(shoe.getModel());
        shoe1.setType(shoe.getType());
        shoe1.setDiscountRate(0);
        shoe1.setAmountOfSold(0);

        shoe1.setPrice( (shoe.getPrice() - (shoe.getPrice() * shoe.getDiscountRate()) / 100));
        final Shoe shoeDb = shoeRepository.save(shoe1);

        return shoeDb;


    }

    public List<Shoe> getAllShoes() {
        List<Shoe> shoes = shoeRepository.findAll();
        List<Shoe> shoe = new ArrayList<>();

        shoes.forEach(it -> {
            Shoe shoe1 = new Shoe();
            shoe1.setId(it.getId());
            shoe1.setSize(it.getSize());
            shoe1.setColour(it.getColour());
            shoe1.setStock(it.getStock());
            shoe1.setDiscountRate(it.getDiscountRate());
            shoe1.setModel(it.getModel());
            shoe1.setPrice(it.getPrice());
            shoe1.setType(it.getType());
            shoe1.setAmountOfSold(it.getAmountOfSold());



            shoe.add(shoe1);
        });
        return shoe;
    }


    public List<Shoe> searchShoesBySize(int size) {

        return (List<Shoe>) shoeRepository.findBySize(size)
                .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this size.Try Later. "));


    }
   public List<Shoe> searchShoesByType(String type) {

       return (List<Shoe>) shoeRepository.findByType(type)
               .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this size.Try Later. "));

   }

        public List<Shoe> searchShoeWithColour(String colour) {


        return (List<Shoe>) shoeRepository.findByColour(colour)
                .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this colour.Try Later. "));

    }

    public List<Shoe> searchShoeByModel(String model) {

        return (List<Shoe>) shoeRepository.findByModel(model)
                .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this model. Try Later."));
    }

    public List<Shoe> searchBySizeAndColour(int size, String colour) {

        return (List<Shoe>) shoeRepository.findBySizeAndColour(size, colour)
                .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this section. Try Later."));
    }

    public List<Shoe> searchByModelAndSize(String model, int size) {

        return (List<Shoe>) shoeRepository.findByModelAndSize(model, size)
                .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this section. Try Later."));
    }

    public List<Shoe> searchByModelAndColour(String model, String colour) {

        return (List<Shoe>) shoeRepository.findByModelAndColour(model, colour)
                .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this section. Try Later."));
    }

    public List<Shoe> searchByModelAndColourAndSize(String model, String colour, int size) {

        return (List<Shoe>) shoeRepository.findByModelAndColourAndSize(model, colour, size)
                .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this section. Try Later."));
    }

  //  public List<Shoe> searchforDiscountedShoes(int discountRate) {


    //        return (List<Shoe>) shoeRepository.findByDiscountRateGreaterThan(0)
  //                  .orElseThrow(() -> new ShoesNotFoundException("There are no shoes in this section. Try Later."));


    // }
    public static <T> void merge(T source, T target) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(source, target);
    }
    public void updateShoe(int id, Shoe newShoe) {



        Shoe shoe = shoeRepository.findById(id).get();
        merge(newShoe, shoe);

        if(newShoe.getDiscountRate()>0){
            shoe.setPrice(shoe.getPrice()-shoe.getPrice()* newShoe.getDiscountRate()/100);
        }
        shoeRepository.save(shoe);


    }

    public void updateShoeDiscount(int id, Shoe newShoe) {

        Shoe myShoe = shoeRepository.findById(id).get();
        myShoe .setDiscountRate(newShoe.getDiscountRate());
       myShoe .setPrice(myShoe .getPrice() - ((myShoe .getPrice() * myShoe .getDiscountRate()) / 100));




        shoeRepository.save(myShoe );

        }



    public Shoe getShoeById(int id) {
        return shoeRepository.findById(id)
                .orElseThrow(() -> new ShoesNotFoundException("Shoe not found with id: " + id));
    }


    public List<Shoe> findBDiscountRateGreaterThan(int discountRate) {
        List<Shoe> response = (List<Shoe>) shoeRepository.findByDiscountRateGreaterThan(discountRate);
        return response;
    }

        public List<Shoe> findAllOrderByAmountOfSoldAsc() {
          List<Shoe> ab= shoeRepository.findAllOrderByAmountOfSoldAsc();

          return ab;

        }

    public List<Shoe> findAllOrderByAmountOfSoldDesc() {
        List<Shoe> abc= shoeRepository.findAllOrderByAmountOfSoldDesc();

        return abc;



    }

    }






