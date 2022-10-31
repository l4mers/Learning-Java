package com.users.users.controller;

import com.users.users.controller.repository.MySqlRepository;
import com.users.users.model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.persistence.JoinTable;
import java.sql.Date;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@RestController
public class UserController {

    @Autowired
    MySqlRepository mySqlRepository; //Interface

    @GetMapping("/get-all-users")
    public List<Users> getAllUsers(){

        return mySqlRepository.findAll();
    }

    @GetMapping("/get-user/{id}")
    public Users getUser(@PathVariable("id") Integer id){

        return mySqlRepository.findById(id).get();
    }

    @DeleteMapping("/remove/{id}")
    public boolean deleteUser(@PathVariable("id") Integer id){
        if(!mySqlRepository.findById(id).equals(Optional.empty())){
            mySqlRepository.deleteById(id);
            return true;
        }
        return false;
    }

    @PutMapping("/update/{id}")
    @JoinTable(name="userdata")
    public Users updateUser (@PathVariable("id") Integer id,
                             @RequestBody Map<String, String> body){ //representerar Json struktur

        Users currentUser = mySqlRepository.findById(id).get();
        currentUser.setPassword(body.get("password")); //map key
        currentUser.setEmail(body.get("email")); //map key
        mySqlRepository.save(currentUser);
        return currentUser;
    }

    @PostMapping("/add")
    @JoinTable(name="userdata")
    public boolean create(@RequestBody Map<String, String> body){

        String userName = body.get("username");
        String password = body.get("password");
        String email = body.get("email");
        String firstname = body.get("firstname");
        String lastname = body.get("lastname");
        String birthdateAsString = body.get("birthdate");
        Date birthdate = Date.valueOf(birthdateAsString);

        if (!mySqlRepository.existsByUsername(userName) || !mySqlRepository.existsByEmail(email)){
            mySqlRepository.save(new Users(userName, password,  email, firstname, lastname, birthdate));
            return true;
        }else{
            return false;
        }
    }
    @PostMapping("/login")
    public boolean validateUser (@RequestBody Map<String, String> body){

        String email = body.get("email");
        if (mySqlRepository.existsByEmail(email)){
            String password = body.get("password");
            Users currentUser = mySqlRepository.getUsersByEmail(email);
            return currentUser.getPassword().equals(password);
        }
        return false;
    }

}
