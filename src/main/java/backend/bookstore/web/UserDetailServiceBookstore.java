package backend.bookstore.web;

import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import backend.bookstore.domain.BookstoreUser;
import backend.bookstore.domain.BookstoreUserRepository;

@Service
public class UserDetailServiceBookstore implements UserDetailsService {
    
    private final BookstoreUserRepository repository;

    public UserDetailServiceBookstore(BookstoreUserRepository bookstoreUserRepository) {
        this.repository = bookstoreUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        BookstoreUser curruser = repository.findByUsername(username);
        UserDetails user = new org.springframework.security.core.userdetails.User(username, curruser.getPasswordHashed(), 
        		AuthorityUtils.createAuthorityList(curruser.getRole()));
        return user;
    }

}

