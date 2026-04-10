package com.example.SwiggyClone.service.security_service.implementation;

import com.example.SwiggyClone.repository.RestaurantRepository;

import java.util.Optional;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.example.SwiggyClone.dto.request.LoginRequestDto;
import com.example.SwiggyClone.dto.request.RegisterRequestDto;
import com.example.SwiggyClone.dto.response.LoginResponseDto;
import com.example.SwiggyClone.dto.response.UserDetailsResponseDto;
import com.example.SwiggyClone.enums.Role;
import com.example.SwiggyClone.exception.DuplicateEntityException;
import com.example.SwiggyClone.exception.ResourceNotFoundException;
import com.example.SwiggyClone.model.Customer;
import com.example.SwiggyClone.model.Delivery;
import com.example.SwiggyClone.model.Restaurant;
import com.example.SwiggyClone.model.User;
import com.example.SwiggyClone.repository.CustomerRepository;
import com.example.SwiggyClone.repository.DeliveryRepository;
import com.example.SwiggyClone.repository.UserRepository;
import com.example.SwiggyClone.service.application_service.UserService;
import com.example.SwiggyClone.service.security_service.AuthService;
import com.example.SwiggyClone.service.security_service.JwtService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class AuthServiceImplementation implements AuthService{
    private AuthenticationManager authenticationManager;
    private RestaurantRepository restaurantRepository;
    private CustomerRepository customerRepository;
    private UserRepository userRepository;
    private DeliveryRepository deliveryRepository;
    private UserService userService;
    private JwtService jwtService;
    private PasswordEncoder passwordEncoder;

    public UserDetailsResponseDto register(RegisterRequestDto registerRequestDto){
        Optional<User> user=userRepository.findByEmail(registerRequestDto.getEmail());
        if(user.isPresent()){
            throw new DuplicateEntityException("User Already Exists");
        }
        User newUser =new User();
        newUser.setEmail(registerRequestDto.getEmail());
        newUser.setImages(registerRequestDto.getImages());
        newUser.setUserName(registerRequestDto.getName());
        newUser.setPhoneNumber(registerRequestDto.getPhoneNumber());
        newUser.setPassword(passwordEncoder.encode(registerRequestDto.getPassword()));
        newUser.setAddress(registerRequestDto.getAddress());
        
        Role role=registerRequestDto.getRole();
        newUser.setRole(role);

        userRepository.save(newUser);

        switch(role)
        {
            case CUSTOMER:
                Customer customer = new Customer();
                customer.setPoints(registerRequestDto.getCustomerDetails().getPoints());
                customer.setRatings(registerRequestDto.getCustomerDetails().getRatings());
                customer.setSubscribed(registerRequestDto.getCustomerDetails().getIsSubscribed());
                customer.setUser(newUser);
                customerRepository.save(customer);
                break;
            case RESTAURANT_OWNER:
                Restaurant restaurant = new Restaurant();
                restaurant.setDescription(registerRequestDto.getRestaurantDetails().getDescription());
                restaurant.setFoodType(registerRequestDto.getRestaurantDetails().getFoodType());
                restaurant.setRatings(registerRequestDto.getRestaurantDetails().getRatings());
                restaurant.setRestName(registerRequestDto.getRestaurantDetails().getRestaurantName());
                restaurant.setRestaurantStatus(registerRequestDto.getRestaurantDetails().getRestaurantStatus());
                restaurant.setUser(newUser);
                restaurantRepository.save(restaurant);
                break;
            case DELIVERY_PARTNER:
                Delivery delivery=new Delivery();
                delivery.setCurrentLoc(registerRequestDto.getDeliveryPartnerDetails().getCurrentLoc());
                delivery.setPoints(registerRequestDto.getDeliveryPartnerDetails().getPoints());
                delivery.setRatings(registerRequestDto.getDeliveryPartnerDetails().getRatings());
                delivery.setUser(newUser);
                deliveryRepository.save(delivery);
                break;
            default:
                break;
        }
        return userService.getUserDetails(newUser.getEmail());
    }
    public LoginResponseDto login(LoginRequestDto loginRequestDto){

        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(loginRequestDto.getEmail(), 
        loginRequestDto.getPassword()));

        User user=userRepository.findByEmail(loginRequestDto.getEmail()).orElseThrow(()->
         new ResourceNotFoundException("User Not Found"));
        String token=jwtService.generateToken(loginRequestDto.getEmail(), user.getRole().name());
        LoginResponseDto loginResponseDto=new LoginResponseDto(token);

        return loginResponseDto;
    }
}
