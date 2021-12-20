package com.getir.ReadingIsGood.controller.customer;

import com.getir.ReadingIsGood.controller.utility.exception.BusinessException;
import com.getir.ReadingIsGood.controller.customer.model.request.AddCustomerRequest;
import com.getir.ReadingIsGood.controller.customer.model.request.AuthenticationRequest;
import com.getir.ReadingIsGood.controller.customer.model.response.AddCustomerResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.AuthenticationResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponse;
import com.getir.ReadingIsGood.service.authentication.MyUserDetailsServiceImpl;
import com.getir.ReadingIsGood.service.customer.CustomerService;
import com.getir.ReadingIsGood.service.order.OrderService;
import com.getir.ReadingIsGood.util.JwtUtil;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.ArrayList;

@RestController
@RequestMapping(value = "/customer")
public class CustomerController {

    private final CustomerService customerService;
    private final OrderService orderService;
    private final CustomerApiMapper customerApiMapper;
    private final AuthenticationManager authenticationManager;
    private final MyUserDetailsServiceImpl myUserDetailsService;
    private final JwtUtil jwtTokenUtil;

    public CustomerController(CustomerService customerService, OrderService orderService, CustomerApiMapper customerApiMapper, AuthenticationManager authenticationManager, MyUserDetailsServiceImpl myUserDetailsService, JwtUtil jwtTokenUtil) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.customerApiMapper = customerApiMapper;
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<AuthenticationResponse> createAuthenticationToken(@Valid @RequestBody AuthenticationRequest authenticationRequest){

        try {
            authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        } catch (Exception ex) {
            throw new BusinessException("Incorrect email or password");
        }
        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(value = "/register")
    public ResponseEntity<AddCustomerResponse> addCustomer(@Valid @RequestBody AddCustomerRequest request){

        var addCustomerResponseDto = customerService.addCustomer(customerApiMapper.mapAddCustomerRequestToCustomerDto(request));
        return ResponseEntity.status(HttpStatus.OK).body(customerApiMapper.mapAddCustomerResponseDtoToAddCustomerResponse(addCustomerResponseDto));
    }

    @GetMapping(value = "/{customerId}/order")
    public ResponseEntity<ArrayList<OrderResponse>> getOrders(@PathVariable int customerId, @RequestParam int page){

        var ordersDto = orderService.getOrders(customerId, page);
        var orders = new ArrayList<OrderResponse>();
        for (var orderResponseDto : ordersDto) {
            orders.add(customerApiMapper.mapOrderResponseDtoToOrderResponse(orderResponseDto));
        }
        return ResponseEntity.status(HttpStatus.OK).body(orders);
    }
}
