package sefa.example.shoes.service;

import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.modelmapper.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;
import org.springframework.stereotype.Service;
import sefa.example.shoes.exception.UserNotFoundException;
import sefa.example.shoes.model.Coupon;
import sefa.example.shoes.model.Shoe;
import sefa.example.shoes.model.User;
import sefa.example.shoes.repository.CouponRepository;
import sefa.example.shoes.repository.ShoeRepository;
import sefa.example.shoes.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Service
@RequiredArgsConstructor
public class UserService{

    private final UserRepository userRepository;
    private final ShoeRepository shoeRepository;

    private final CouponRepository couponRepository;


    public User saveUser (User  user) {


        User user1 = new User();
        user1.setId(user.getId());
        user1.setBalance(user.getBalance());
        user1.setName(user.getName());
        user1.setEmail(user.getEmail());
        user1.setPassword(user.getPassword());
        user1.setBuy(false);
        user1.setUserId(user.getUserId());
        user1.setPurchaseAmount(0L);
        user1.setCoupons(user.getCoupons());
        user1.setUseCoupon(false);



        final User userDb = userRepository.save(user1);

        return userDb;


    }


    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        List<User> users1 = new ArrayList<>();

        users.forEach(it -> {
            User user =new User();
            user.setId(it.getId());
            user.setUserId(it.getUserId());
            user.setPassword(it.getPassword());
            user.setName(it.getName());
            user.setEmail(it.getEmail());
            user.setBalance(it.getBalance());
            user.setCoupons(it.getCoupons());
            user.setUseCoupon(it.isUseCoupon());



            users1.add(user);
        });
        return users1;
    }
    public User getUserById(int id) {
        return  userRepository.findById(id)
                .orElseThrow(() -> new UserNotFoundException("User not found with id: " +  id));
    }

    public void deleteUser(int id){
        userRepository.deleteById(id);

    }

     public void buyShoe(int id,int shoeId,User newUser ) {

         User user = userRepository.findById(id).get();
         Shoe shoe = shoeRepository.findById(shoeId).get();



         // int couponId = user.getCoupons().get(couponRepository.)

         //  Coupon coupon = couponRepository.findById(couponId)

         user.setBuy(true);
         user.setUseCoupon(newUser.isUseCoupon());


         if(user.isUseCoupon()==true){

             user.setSelectedCoupon(couponRepository.findById(newUser.getSelectedCoupon().getId()));
             user.getCoupons().add(user.getSelectedCoupon());
             shoe.setAmountOfSold((int) (shoe.getAmountOfSold() + 1 * user.getPurchaseAmount()));
         }



         if (  user.isUseCoupon() == false) {

             user.setBalance((int) (user.getBalance() - shoe.getPrice() * user.getPurchaseAmount()));
             shoe.setAmountOfSold((int) (shoe.getAmountOfSold() + 1 * user.getPurchaseAmount()));

         } else if ( user.isUseCoupon() == true) {

             if (user.getCoupons().contains(user.getSelectedCoupon())) {
                 user.setBalance((int) (user.getSelectedCoupon().getCouponAmount() + user.getBalance() - shoe.getPrice() * user.getPurchaseAmount()));
                 shoe.setAmountOfSold((int) (shoe.getAmountOfSold() + 1 * newUser.getPurchaseAmount()));
             }else{
                 System.out.println("Error !");
             }

         }
     }
    public void defineCouponforUsers(int id,User newUser){


        User user = userRepository.findById(id).get();

        List<Coupon> newCoupon = user.getCoupons();

        newCoupon.addAll( newUser.getCoupons());

        user.setCoupons(newCoupon);


        userRepository.save(user);

   }

    public static <T> void merge(T source, T target) {
        ModelMapper modelMapper = new ModelMapper();
        modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        modelMapper.map(source, target);
    }
    public void updateUserUseCoupon(int id, User newUser) {


        User myUser = userRepository.findById(id).get();
        newUser.setUseCoupon(true);
        myUser.setUseCoupon(newUser.isUseCoupon());

        userRepository.save(myUser);



    }

   /* public void buyShoeWithCoupon(int id,int shoeId,User newUser,Coupon newCoupon){

        Coupon coupon = new Coupon();
        User user = userRepository.findById(id).get();
        Shoe shoe = shoeRepository.findById(shoeId).get();

        coupon.setCouponAmount(newCoupon.getCouponAmount());

        if(user.getCoupons() != null && user.isBuy()== true){

        user.setBalance((int) (user.getBalance() + coupon.getCouponAmount()));
            user.setBalance((int) (user.getBalance()-shoe.getPrice()* user.getPurchaseAmount()));
            shoe.setAmountOfSold(shoe.getAmountOfSold() + 1);

        }
    }*/




}
