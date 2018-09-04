package ru.myori.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.Assert;
import ru.myori.AuthorizedUser;
import ru.myori.model.Customer;
import ru.myori.model.User;
import ru.myori.repository.customer.CustomerRepository;
import ru.myori.repository.user.UserRepository;
import ru.myori.to.UserTo;
import ru.myori.util.exception.NotFoundException;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

import static ru.myori.util.UserUtil.prepareToSave;
import static ru.myori.util.UserUtil.updateFromTo;
import static ru.myori.util.ValidationUtil.checkNotFound;
import static ru.myori.util.ValidationUtil.checkNotFoundWithId;

@Service("userService")
public class UserServiceImpl implements UserService, UserDetailsService {

    private final UserRepository userRepository;

    private final CustomerRepository customerRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, CustomerRepository customerRepository) {
        this.userRepository = userRepository;
        this.customerRepository = customerRepository;
    }

    @Override
    public AuthorizedUser loadUserByUsername(String email) throws UsernameNotFoundException {
        User user = userRepository.getByEmail(email.toLowerCase());
        if (user == null) {
            throw new UsernameNotFoundException("User " + email + " is not found");
        }
        return new AuthorizedUser(user);
    }

    @Override
    public User create(User user) {
        Assert.notNull(user, "user must not be null");
//        Assert.isNull(getByEmail(user.getEmail()),"User with this email already exists!");
        return userRepository.save(user);
    }

    @Override
    public void delete(int id) throws NotFoundException {
        checkNotFoundWithId(userRepository.delete(id), id);
    }

    @Override
    public User get(int id) throws NotFoundException {
        return checkNotFoundWithId(userRepository.get(id), id);
    }

    @Override
    public User getByEmail(String email) throws NotFoundException {
        Assert.notNull(email, "email must not be null");
        return checkNotFound(userRepository.getByEmail(email), "email=" + email);
    }

    @Override
    public void update(User user) {
        Assert.notNull(user, "user must not be null");
//        Assert.isNull(getByEmail(user.getEmail()),"User with this email already exists!");
        userRepository.save(user);
    }

    @Override
    public void evictCache() {

    }

    @Override
    public void update(UserTo userTo) {
        User user = updateFromTo(get(userTo.getId()), userTo);
        Assert.isNull(getByEmail(user.getEmail()), "User with this email already exists!");
        userRepository.save(prepareToSave(user));
    }

    @Override
    public List<User> getAll() {
        return userRepository.getAll();
    }

    @Override
    public Set<Customer> getCustomers(int userId){
        List<Customer> customerList=customerRepository.getAll(userId);
//        Set<CustomerTo> customerToSet=customerList.stream().map(customer -> new CustomerTo(customer)).collect(Collectors.toSet());
        return new HashSet<>(customerList);
    }

    @Override
    @Transactional
    public void enable(int id, boolean enabled) {
        User user = get(id);
        user.setEnabled(enabled);
        userRepository.save(user);
    }

}
