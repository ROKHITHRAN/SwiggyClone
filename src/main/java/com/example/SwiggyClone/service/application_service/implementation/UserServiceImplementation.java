package com.example.SwiggyClone.service.application_service.implementation;

import org.springframework.stereotype.Service;

import com.example.SwiggyClone.dto.request.CustomerRequestDto;
import com.example.SwiggyClone.dto.request.DeliveryRequestDto;
import com.example.SwiggyClone.dto.request.RestaurantRequestDto;
import com.example.SwiggyClone.dto.request.UserDetailsEditRequestDto;
import com.example.SwiggyClone.dto.response.CustomerDetailsResponseDto;
import com.example.SwiggyClone.dto.response.DeliveryDetailsResponseDto;
import com.example.SwiggyClone.dto.response.RestaurantDetailsResponseDto;
import com.example.SwiggyClone.dto.response.UserDetailsResponseDto;
import com.example.SwiggyClone.enums.Role;
import com.example.SwiggyClone.model.Customer;
import com.example.SwiggyClone.model.Delivery;
import com.example.SwiggyClone.model.Restaurant;
import com.example.SwiggyClone.model.User;
import com.example.SwiggyClone.repository.CustomerRepository;
import com.example.SwiggyClone.repository.DeliveryRepository;
import com.example.SwiggyClone.repository.RestaurantRepository;
import com.example.SwiggyClone.repository.UserRepository;
import com.example.SwiggyClone.service.application_service.UserService;

import jakarta.transaction.Transactional;

import com.example.SwiggyClone.exception.ResourceNotFoundException;

@Service
public class UserServiceImplementation implements UserService
{
    private UserRepository userRepository;
    private CustomerRepository customerRepository;
    private RestaurantRepository restaurantRepository;
    private DeliveryRepository deliveryRepository;

    public UserDetailsResponseDto getUserDetils(String email)
    {
        User user = userRepository.findByEmail(email).orElseThrow(()-> 
        new ResourceNotFoundException("User not found"));

        Role role = user.getRole();
        UserDetailsResponseDto userDetailsResponseDto = new UserDetailsResponseDto();
        userDetailsResponseDto.setAddress(user.getAddress());
        userDetailsResponseDto.setEmail(user.getEmail());
        userDetailsResponseDto.setImages(user.getImages());
        userDetailsResponseDto.setName(user.getUserName());
        userDetailsResponseDto.setPhoneNumber(user.getPhoneNumber());
        userDetailsResponseDto.setUserId(user.getUserId());

        switch(role)
        {
            case CUSTOMER:
                Customer customer = customerRepository.findById(user.getUserId()).orElseThrow(()-> 
                new ResourceNotFoundException("Customer not found"));

                CustomerDetailsResponseDto customerDetailsResponseDto = new CustomerDetailsResponseDto();
                customerDetailsResponseDto.setPoints(customer.getPoints());
                customerDetailsResponseDto.setRatings(customer.getRatings());
                customerDetailsResponseDto.setSubscribed(customer.isSubscribed());
                userDetailsResponseDto.setCustomerDetails(customerDetailsResponseDto);
                break;
            case RESTAURANT_OWNER:
                Restaurant restaurant = restaurantRepository.findById(user.getUserId()).orElseThrow(()-> 
                new ResourceNotFoundException("Customer not found"));

                RestaurantDetailsResponseDto restaurantDetailsResponseDto = new RestaurantDetailsResponseDto();
                restaurantDetailsResponseDto.setDescription(restaurant.getDescription());
                restaurantDetailsResponseDto.setFoodType(restaurant.getFoodType());
                restaurantDetailsResponseDto.setRatings(restaurant.getRatings());
                restaurantDetailsResponseDto.setRestaurantName(restaurant.getRestName());
                restaurantDetailsResponseDto.setRestaurantStatus(restaurant.getRestaurantStatus());

                userDetailsResponseDto.setRestaurantDetails(restaurantDetailsResponseDto);
                break;
            case DELIVERY_PARTNER:
                Delivery delivery = deliveryRepository.findById(user.getUserId()).orElseThrow(()-> 
                new ResourceNotFoundException("Delivery not found"));

                DeliveryDetailsResponseDto deliveryDetailsResponseDto = new DeliveryDetailsResponseDto();
                deliveryDetailsResponseDto.setCurrentLoc(delivery.getCurrentLoc());
                deliveryDetailsResponseDto.setPoints(delivery.getPoints());
                deliveryDetailsResponseDto.setRatings(delivery.getRatings());

                userDetailsResponseDto.setDeliveryPartnerDetails(deliveryDetailsResponseDto);
                break;
            default:
                break;
        }

        return userDetailsResponseDto;
    } 

    @Transactional
    public UserDetailsResponseDto editUserDetails(UserDetailsEditRequestDto dto) {

        User user = userRepository.findByEmail(dto.getEmail())
                .orElseThrow(() -> new ResourceNotFoundException("User not found"));

        if (dto.getName() != null && !dto.getName().trim().isEmpty()) {
            user.setUserName(dto.getName());
        }

        if (dto.getPhoneNumber() != null && !dto.getPhoneNumber().trim().isEmpty()) {
            user.setPhoneNumber(dto.getPhoneNumber());
        }

        if (dto.getPassword() != null && !dto.getPassword().trim().isEmpty()) {
            user.setPassword(dto.getPassword()); // ⚠️ encode in real app
        }

        if (dto.getAddress() != null) {
            user.setAddress(dto.getAddress());
        }

        if (dto.getImages() != null) {
            user.setImages(dto.getImages());
        }

        userRepository.save(user);

        // 3. Role-based update
        Role role = user.getRole();

        switch (role) {

            case CUSTOMER:
                if (dto.getCustomerDetails() != null) {
                    Customer customer = customerRepository.findById(user.getUserId())
                            .orElseThrow(() -> new ResourceNotFoundException("Customer not found"));

                    CustomerRequestDto cDto = dto.getCustomerDetails();

                    if (cDto.getPoints() != null) {
                        customer.setPoints(cDto.getPoints());
                    }

                    if (cDto.getRatings() != null) {
                        customer.setRatings(cDto.getRatings());
                    }

                    if (cDto.getIsSubscribed() != null) {
                        customer.setSubscribed(cDto.getIsSubscribed());
                    }

                    customerRepository.save(customer);
                }
                break;

            case RESTAURANT_OWNER:
                if (dto.getRestaurantDetails() != null) {
                    Restaurant restaurant = restaurantRepository.findById(user.getUserId())
                            .orElseThrow(() -> new ResourceNotFoundException("Restaurant not found"));

                    RestaurantRequestDto rDto = dto.getRestaurantDetails();

                    if (rDto.getDescription() != null) {
                        restaurant.setDescription(rDto.getDescription());
                    }

                    if (rDto.getFoodType() != null) {
                        restaurant.setFoodType(rDto.getFoodType());
                    }

                    if (rDto.getRestaurantName() != null) {
                        restaurant.setRestName(rDto.getRestaurantName());
                    }

                    if (rDto.getRestaurantStatus() != null) {
                        restaurant.setRestaurantStatus(rDto.getRestaurantStatus());
                    }

                    restaurantRepository.save(restaurant);
                }
                break;

            case DELIVERY_PARTNER:
                if (dto.getDeliveryPartnerDetails() != null) {
                    Delivery delivery = deliveryRepository.findById(user.getUserId())
                            .orElseThrow(() -> new ResourceNotFoundException("Delivery not found"));

                    DeliveryRequestDto dDto = dto.getDeliveryPartnerDetails();

                    if (dDto.getCurrentLoc() != null) {
                        delivery.setCurrentLoc(dDto.getCurrentLoc());
                    }

                    if (dDto.getPoints() != null) {
                        delivery.setPoints(dDto.getPoints());
                    }

                    if (dDto.getRatings() != null) {
                        delivery.setRatings(dDto.getRatings());
                    }

                    deliveryRepository.save(delivery);
                }
                break;

            default:
                break;
        }

        return getUserDetils(user.getEmail());
    }
}
