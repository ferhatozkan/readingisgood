package com.getir.ReadingIsGood.service.authentication;

import com.getir.ReadingIsGood.repository.customer.CustomerRepository;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class MyUserDetailsServiceImpl implements UserDetailsService {

    private final CustomerRepository customerRepository;

    public MyUserDetailsServiceImpl(CustomerRepository customerRepository) {
        this.customerRepository = customerRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        var customer = customerRepository.getByEmail(email);
        if (customer == null) throw new UsernameNotFoundException("Incorrect email or password");
        return new User(customer.getEmail(), customer.getPassword(), new ArrayList<>());
    }
}
