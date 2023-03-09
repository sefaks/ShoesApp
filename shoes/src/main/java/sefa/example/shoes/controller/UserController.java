package sefa.example.shoes.controller;

import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import sefa.example.shoes.model.Coupon;
import sefa.example.shoes.model.Shoe;
import sefa.example.shoes.model.User;
import sefa.example.shoes.service.UserService;

import java.util.List;

import static org.springframework.http.HttpStatus.CREATED;

@RestController
@RequestMapping("/users")
@RequiredArgsConstructor
public class UserController {


    private final UserService userService;

    @PostMapping(path="/add-user")
    public ResponseEntity<User> createUser(@RequestBody User user) {
        return  new ResponseEntity<>(userService.saveUser(user), CREATED);
    }

    @GetMapping("/getall")
    public ResponseEntity<List<User>> getAll() {
        return ResponseEntity.ok(userService.getAll());
    }

    @GetMapping("/{id}")
    private User getUserById( @PathVariable int id) {
        return userService.getUserById(id);
    }

    @DeleteMapping("/{id}")

    public ResponseEntity<Void> deleteUser(@PathVariable  int id){
        userService.deleteUser(id);

        return new ResponseEntity<>(HttpStatus.OK);

    }
    @PostMapping("/admin/edit/coupon/id/{id}")
    public void  updateUserCoupon( @PathVariable int id,@RequestBody User newUser)
    {
        userService.updateUserUseCoupon(id,newUser);
    }

    @PostMapping("/define/coupon/id/{id}")
    public void  userDefineCoupon( @PathVariable int id,@RequestBody User newUser)
    {
        userService.defineCouponforUsers(id, newUser);
    }

     @PostMapping("/user/buy/shoe/id/{id}/shoeId/{shoeId}")
    public void  userBuyShoe( @PathVariable int id,@PathVariable int shoeId,@RequestBody  User newUser)
    {
      userService.buyShoe(id,shoeId,newUser);
    }


}
