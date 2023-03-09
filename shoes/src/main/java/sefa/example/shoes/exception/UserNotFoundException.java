package sefa.example.shoes.exception;

public class UserNotFoundException  extends RuntimeException{


    public UserNotFoundException(String msg){
        super(msg);
    }

         /*   public void buyShoe(int id,int shoeId,User newUser,Coupon coupon){

           // User user = userRepository.findById(id).get();

            Shoe shoe = shoeRepository.findById(shoeId).get();




            user.setName(user.getName());
            user.setUserId(user.getUserId());
            user.setEmail(user.getEmail());
            user.setBalance(user.getBalance());
            user.setId(user.getId());
            user.setBuy(true);
            user.setCoupons(coupon.getUser().getCoupons());


            user.setPurchaseAmount(newUser.getPurchaseAmount());


            if(user.isBuy() == true){
                user.setBalance((int) (user.getBalance() + coupon.getCouponAmount()));

                user.setBalance((int) (user.getBalance()-shoe.getPrice()* user.getPurchaseAmount()));
                shoe.setAmountOfSold((int) (shoe.getAmountOfSold() + 1 * user.getPurchaseAmount()));

            }

      //  }else if(user.isBuy() == true && user.getCoupons() != null){


        //    user.setBalance((int) (user.getBalance() + coupon.getCouponAmount()));
     //       user.setBalance((int) (user.getBalance()-shoe.getPrice()* user.getPurchaseAmount()));
       //     shoe.setAmountOfSold(shoe.getAmountOfSold() + 1);


        shoeRepository.save(shoe);
        userRepository.save(user);




    } */


    //  public static <T> void mergee(T source, T target) {
    //      ModelMapper modelMapper = new ModelMapper();
    //      modelMapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
    //      modelMapper.map(source, target);
    //   }
}

