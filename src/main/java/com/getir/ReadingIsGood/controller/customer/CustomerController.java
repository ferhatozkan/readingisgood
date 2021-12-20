package com.getir.ReadingIsGood.controller.customer;

import com.getir.ReadingIsGood.controller.customer.model.request.AddCustomerRequest;
import com.getir.ReadingIsGood.controller.customer.model.request.AuthenticationRequest;
import com.getir.ReadingIsGood.controller.customer.model.response.AuthenticationResponse;
import com.getir.ReadingIsGood.controller.customer.model.response.OrderResponseViewModel;
import com.getir.ReadingIsGood.service.GenericResponse;
import com.getir.ReadingIsGood.service.authentication.MyUserDetailsServiceImpl;
import com.getir.ReadingIsGood.service.customer.CustomerService;
import com.getir.ReadingIsGood.service.order.OrderService;
import com.getir.ReadingIsGood.util.JwtUtil;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

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

    public CustomerController(CustomerService customerService,
                              OrderService orderService,
                              CustomerApiMapper customerApiMapper,
                              AuthenticationManager authenticationManager,
                              MyUserDetailsServiceImpl myUserDetailsService,
                              JwtUtil jwtTokenUtil) {
        this.customerService = customerService;
        this.orderService = orderService;
        this.customerApiMapper = customerApiMapper;
        this.authenticationManager = authenticationManager;
        this.myUserDetailsService = myUserDetailsService;
        this.jwtTokenUtil = jwtTokenUtil;
    }

    @PostMapping(value = "/authenticate")
    public ResponseEntity<?> createAuthenticationToken(@RequestBody AuthenticationRequest authenticationRequest) throws Exception{

        try {
            authenticationManager.authenticate( new UsernamePasswordAuthenticationToken(authenticationRequest.getEmail(), authenticationRequest.getPassword()));
        }
        catch(BadCredentialsException ex){
            throw new Exception("Incorrect email or password");
        }

        final UserDetails userDetails = myUserDetailsService.loadUserByUsername(authenticationRequest.getEmail());

        final String jwt = jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new AuthenticationResponse(jwt));
    }

    @PostMapping(value = "/register")
    public GenericResponse<Integer> addCustomer(@RequestBody AddCustomerRequest request){

        GenericResponse<Integer> result = customerService.addCustomer(customerApiMapper.mapAddCustomerRequestToCustomerDto(request));

        return result;
    }

    @GetMapping(value = "/{customerId}/order")
    public GenericResponse<ArrayList<OrderResponseViewModel>> getOrders(@PathVariable int customerId, @RequestParam int page){

        var result = orderService.getOrders(customerId, page);

        var response = new GenericResponse<ArrayList<OrderResponseViewModel>>();
        response.setSuccess(true);

        if(!result.isSuccess()){
            response.setSuccess(false);
            response.setMessage(result.getMessage());
            return response;
        }

        var orders = new ArrayList<OrderResponseViewModel>();

        for (var order : result.getData()) {
            orders.add(customerApiMapper.mapOrderResponseDtoToOrderResponseViewModel(order));
        }

        response.setData(orders);
        return response;
    }
}
