package com.jrn.journalapp.service;

import com.jrn.journalapp.entity.User;
import com.jrn.journalapp.repo.Userrepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

@Component
@Service
public class UserDetailServiceimp implements UserDetailsService {
    @Autowired
    private Userrepo userepo;
    @Override // for fetching userdetails
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException{
        User user = userepo.findByusername(username);
        if(user!=null){
            return org.springframework.security.core.userdetails.User.builder()
                    .username(user.getUsername())
                    .password(user.getPassword())
                    .roles(user.getRoles().toArray(new String[0]))//instaed of new String[0]we can add any datatype for which our array of data should we returning
                    .build();
        }
        throw new UsernameNotFoundException("User not found with username: " + username);
    }
}
