package com.example.SwiggyClone.service.application_service.implementation;

import java.util.ArrayList;
import java.util.List;

import org.springframework.stereotype.Service;

import com.example.SwiggyClone.dto.request.CartItemAddRequestDto;
import com.example.SwiggyClone.dto.request.CartUpdateRequestDto;
import com.example.SwiggyClone.dto.request.OrderAddRequestDto;
import com.example.SwiggyClone.dto.request.OrderItemDto;
import com.example.SwiggyClone.dto.response.CartDetailsResponseDto;
import com.example.SwiggyClone.dto.response.OrderResponseDto;
import com.example.SwiggyClone.enums.OrderStatus;
import com.example.SwiggyClone.exception.ResourceNotFoundException;
import com.example.SwiggyClone.model.Cart;
import com.example.SwiggyClone.model.CartItems;
import com.example.SwiggyClone.model.Customer;
import com.example.SwiggyClone.model.FoodItems;
import com.example.SwiggyClone.model.OrderItems;
import com.example.SwiggyClone.model.Orders;
import com.example.SwiggyClone.model.Payment;
import com.example.SwiggyClone.model.Restaurant;
import com.example.SwiggyClone.repository.CartRepository;
import com.example.SwiggyClone.repository.CustomerRepository;
import com.example.SwiggyClone.repository.FoodItemRepository;
import com.example.SwiggyClone.repository.OrderRepository;
import com.example.SwiggyClone.repository.PaymentRepository;
import com.example.SwiggyClone.repository.RestaurantRepository;
import com.example.SwiggyClone.service.application_service.CartService;
import com.example.SwiggyClone.service.application_service.OrderService;

import jakarta.transaction.Transactional;
import lombok.AllArgsConstructor;


@Service
@AllArgsConstructor
public class CartServiceImplementation implements CartService{

    private CartRepository cartRepository;
    private FoodItemRepository foodItemRepository;
    private RestaurantRepository restaurantRepository;
    private CustomerRepository customerRepository;
    private PaymentRepository paymentRepository;
    private OrderRepository orderRepository;
    private OrderService orderService;
    @Transactional
    @Override
    public CartDetailsResponseDto addItemToCart(CartItemAddRequestDto item)
    {

        Cart cart=new Cart();
        FoodItems food=foodItemRepository.findById(item.getFoodItemId()).orElseThrow(()-> 
        new ResourceNotFoundException("Food Item is Not Found"));

        Restaurant restaurant=restaurantRepository.findById(item.getRestaurantId()).orElseThrow(()-> 
        new ResourceNotFoundException("Restaurant is Not Found"));

        Customer customer=customerRepository.findById(item.getCustomerId()).orElseThrow(()-> 
        new ResourceNotFoundException("Customer is Not Found"));

        int price =food.getCost();
        int quantity=item.getQuantity();
        cart.setAmount((double)(price*quantity));
        cart.setCustomer(customer);
        cart.setRestaurant(restaurant);
          
        CartItems cart_items=new CartItems();
        cart_items.setFoodItems(food);
        cart_items.setQuantity(item.getQuantity());

        cart.addCartItem(cart_items);
        
        customer.setCart(cart);
        cartRepository.save(cart);

        return getCartDetails(cart.getCustomer().getUserId());
    }

    @Transactional
    @Override
    public CartDetailsResponseDto updateCartItem(CartUpdateRequestDto item)
    {

        Cart cart=cartRepository.findById(item.getCartId()).orElseThrow(()->
        new ResourceNotFoundException("Cart is Not Found"));
          
        List<CartItems>cartItems=cart.getCartItems();
        
        for(CartItems it:cartItems){
            if(it.getFoodItems().getFoodId().equals(item.getFoodItemId()))
            {
                if(item.getQuantity()>it.getQuantity()){
                    int change=item.getQuantity()-it.getQuantity();
                    cart.setAmount(cart.getAmount()+it.getFoodItems().getCost()*change);
                }
                else{
                    int change=it.getQuantity()-item.getQuantity();
                    cart.setAmount(cart.getAmount()-it.getFoodItems().getCost()*change);
                }

                it.setQuantity(item.getQuantity());
                return getCartDetails(cart.getCustomer().getUserId());
            }
        }

        FoodItems food=foodItemRepository.findById(item.getFoodItemId()).orElseThrow(()-> 
        new ResourceNotFoundException("Food Item is Not Found"));

        CartItems cart_items=new CartItems();
        cart_items.setFoodItems(food);
        cart_items.setQuantity(item.getQuantity());

        cart.setAmount(cart.getAmount()+item.getQuantity()*food.getCost());

        cart.addCartItem(cart_items);

        return getCartDetails(cart.getCustomer().getUserId());
    }
    
    @Override
    public String deleteCart(Long cartId) throws ResourceNotFoundException
    {

       if(cartRepository.findById(cartId).isPresent())
            cartRepository.deleteById(cartId);
       else
           throw new ResourceNotFoundException("Cart Id is not Found");

        return "Cart Deleted Successfully";
       
    }
    
    @Override
    @Transactional
    public OrderResponseDto checkOutCart(OrderAddRequestDto cart){

        Restaurant restaurant=restaurantRepository.findById(cart.getRestaurantId()).orElseThrow(()-> 
        new ResourceNotFoundException("Restaurant is Not Found"));

        Customer customer=customerRepository.findById(cart.getCustomerId()).orElseThrow(()-> 
        new ResourceNotFoundException("Customer is Not Found"));

        Payment payment=paymentRepository.findById(cart.getPaymentId()).orElseThrow(()-> 
        new ResourceNotFoundException("Payment is Not Found"));

        Orders order=new Orders();
        order.setRestaurant(restaurant);
        order.setAmount(cart.getAmount());
        order.setCustomer(customer);
        order.setDelivery(null);
        order.setOrderStatus(OrderStatus.PLACED);
        order.setPayment(payment);
         
        for (OrderItemDto cartItems : cart.getCartItems())
        {
            FoodItems food = foodItemRepository.findById(cartItems.getFoodItemId())
                    .orElseThrow(() -> new ResourceNotFoundException("Food Item is Not Found"));

            OrderItems orderItem = new OrderItems();
            orderItem.setFoodItems(food);
            orderItem.setQuantity(cartItems.getQuantity());
            order.addOrderItem(orderItem);
        }
        
        Orders saved_order = orderRepository.save(order);

        return orderService.getOrderByOrderId(saved_order.getOrderId());
    }

    @Override
    public CartDetailsResponseDto getCartDetails(Long customerId)
    {

        Customer customer=customerRepository.findById(customerId).orElseThrow(()-> 
        new ResourceNotFoundException("Customer is Not Found"));

        Cart cart=customer.getCart();

        CartDetailsResponseDto cartDetailsResponseDto =new CartDetailsResponseDto();
        cartDetailsResponseDto.setRestaurantId(cart.getRestaurant().getUserId());
        cartDetailsResponseDto.setAmount(cart.getAmount());

        List<OrderItemDto>orderItemsDto = new ArrayList<>();

        for(CartItems item :cart.getCartItems())
        {
            OrderItemDto orderItemDto=new OrderItemDto();
            orderItemDto.setFoodItemId(item.getFoodItems().getFoodId());
            orderItemDto.setQuantity(item.getQuantity());

            orderItemsDto.add(orderItemDto);
        }
        
        cartDetailsResponseDto.setCartItems(orderItemsDto);

        return cartDetailsResponseDto;
    }
}
