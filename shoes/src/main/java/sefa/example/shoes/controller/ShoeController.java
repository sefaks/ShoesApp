package sefa.example.shoes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

import sefa.example.shoes.model.Shoe;
import sefa.example.shoes.service.ShoeService;

import java.util.List;

import static org.springframework.http.HttpStatus.OK;

@RestController
@RequestMapping("/shoes")
@RequiredArgsConstructor
public class ShoeController {

    private final ShoeService shoeService;


    @PostMapping("/add-shoe")
    public ResponseEntity<Shoe> kaydet(@Valid @RequestBody Shoe shoe) {
        return ResponseEntity.ok(shoeService.saveShoe(shoe));
    }

    @GetMapping("/getall")
    public ResponseEntity<List<Shoe>> getAllShoes() {
        return ResponseEntity.ok(shoeService.getAllShoes());

    }
    @GetMapping("/id/{id}")
    public ResponseEntity<Shoe> getShoe(@PathVariable int id) {
        return new ResponseEntity<>(shoeService.getShoeById(id), OK);
    }


    @GetMapping("/{size}")
    public ResponseEntity<List<Shoe>> getSShoes(@PathVariable int size) {
        return ResponseEntity.ok(shoeService.searchShoesBySize(size));
    }
    @GetMapping("/colour/{colour}")
    public ResponseEntity<List<Shoe>> getShoesWithhColour(@PathVariable String colour) {
        return ResponseEntity.ok(shoeService.searchShoeWithColour(colour));
    }

   @GetMapping("/type/{type}")
    public ResponseEntity<List<Shoe>> getShoesWithType(@PathVariable String type) {
       return ResponseEntity.ok(shoeService.searchShoesByType(type));

   }
    @GetMapping("/model/{model}")
    public ResponseEntity<List<Shoe>> getShoeWithModel(@PathVariable String model) {
        return ResponseEntity.ok(shoeService.searchShoeByModel(model));
    }

    @GetMapping("/size/{size}/colour/{colour}")
    public ResponseEntity<List<Shoe>> getShoeWithSizeAndColour(@PathVariable("size") int size, @PathVariable("colour") String colour) {
        return ResponseEntity.ok(shoeService.searchBySizeAndColour(size, colour));
    }

    @GetMapping("/model/{model}/size/{size}")
    public ResponseEntity<List<Shoe>> getShoeWithModelAndSize(@PathVariable("model") String model, @PathVariable("size") int size) {
        return ResponseEntity.ok(shoeService.searchByModelAndSize(model, size));
    }

    @GetMapping("/model/{model}/colour/{colour}")
    public ResponseEntity<List<Shoe>> getShoeWithModelAndColour(@PathVariable("model") String model, @PathVariable("colour") String colour) {
        return ResponseEntity.ok(shoeService.searchByModelAndColour(model, colour));
    }

    @GetMapping("/model/{model}/colour/{colour}/size/{size}")
    public ResponseEntity<List<Shoe>> getShoeWithModelAndColourAndSize(@PathVariable("model") String model, @PathVariable("colour") String colour, @PathVariable("size") int size) {
        return ResponseEntity.ok(shoeService.searchByModelAndColourAndSize(model, colour, size));
    }
    @PostMapping("/admin/edit/id/{id}")
    public void  updateShoe( @PathVariable int id,@RequestBody Shoe newShoe)
    {
        shoeService.updateShoe(id,newShoe);
    }

    @PostMapping("/admin/edit/discount/id/{id}")
    public void  updateShoeDiscouunt( @PathVariable int id,@RequestBody Shoe newShoe)
    {
        shoeService.updateShoeDiscount(id,newShoe);
    }


    @GetMapping(value = "/asc/sold")
    public List<Shoe> getShoesBySoldAsc() {

        return shoeService.findAllOrderByAmountOfSoldAsc();
    }
    @GetMapping(value = "/desc/sold")
    public List<Shoe>getShoesBySoldDesc() {

        return shoeService.findAllOrderByAmountOfSoldDesc();
    }


    @RequestMapping(value = "/greaterthan/{discountRate}", method = RequestMethod.GET)
    @ResponseBody
    public List<Shoe> findByDiscountNumberGreaterThan(@PathVariable int discountRate) {

        List<Shoe> shoeResponse = (List<Shoe>) shoeService.findBDiscountRateGreaterThan(discountRate);
        return shoeResponse;


    }
}
