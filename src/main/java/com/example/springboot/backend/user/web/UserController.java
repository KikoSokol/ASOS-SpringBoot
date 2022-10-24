package com.example.springboot.backend.user.web;


import com.example.springboot.backend.InsuranceSystemService;
import com.example.springboot.backend.address.Address;
import com.example.springboot.backend.address.attribute.PostalCodeParseException;
import com.example.springboot.backend.user.domain.User;
import com.example.springboot.backend.user.domain.attribute.PersonalNumber;
import com.example.springboot.backend.user.domain.attribute.PersonalNumberParseException;
import com.example.springboot.backend.user.web.resource.UserResource;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.Optional;

@RestController
@RequestMapping("/user")
public class UserController {
    private final InsuranceSystemService insuranceSystemService;

    @Autowired
    public UserController(InsuranceSystemService insuranceSystemService) {
        this.insuranceSystemService = insuranceSystemService;
    }

    @GetMapping("/")
    public ResponseEntity all() {
        return new ResponseEntity<>(insuranceSystemService.getInformationUserService().getAllUsers().values(), HttpStatus.OK);
    }

    @GetMapping("/id/{id}")
    public ResponseEntity byId(@PathVariable long id) {
        Optional<User> optionalUser = insuranceSystemService.getUserService().findUserById(id);
        if (optionalUser.isPresent()) {
            User user = optionalUser.get();

            return new ResponseEntity<>(new UserResource(user), HttpStatus.OK);
        }
        return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);
    }

    @PostMapping("/add")
    public ResponseEntity submitNewUser(@RequestBody UserResource userResource)
    {
        if(!userResource.isCorespondenceCorrect())
        {
            return new ResponseEntity<>("Correspondence address is wrong. All fields of correspondence address must be fill or all fields of correspondence address can not be fill", HttpStatus.BAD_REQUEST);
        }

        try
        {
            String name = userResource.getName();
            String surname = userResource.getSurname();
            PersonalNumber personalNumber = new PersonalNumber(userResource.getPersonalNumber());
            String email = userResource.getEmail();
            Address permanentAddress = userResource.getPermanentAddress();
            Address corespondenceAddress = userResource.getCorespondenceAddress();
            User user = this.insuranceSystemService.getUserService().registerUserWithCorespondenceAddress(name,surname,personalNumber,email,permanentAddress,corespondenceAddress);
            return new ResponseEntity<>(user,HttpStatus.CREATED);
        }
        catch (PersonalNumberParseException | PostalCodeParseException e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);

        }

    }


    @PutMapping("/update/submit/{id}")
    public ResponseEntity updateUserSubmit(@PathVariable long id, @RequestBody UserResource userResource)
    {
        if(!userResource.isCorespondenceCorrect())
        {
            return new ResponseEntity<>("Correspondence address is wrong. All fields of correspondence address must be fill or all fields of correspondence address can not be fill", HttpStatus.BAD_REQUEST);
        }


        Optional<User> userOptional = this.insuranceSystemService.getUserService().findUserById(id);

        if(userOptional.isEmpty())
            return new ResponseEntity<>(null, HttpStatus.NOT_FOUND);

        User oldUser = userOptional.get();
        try
        {
            User user = userResource.toUser(oldUser.getIdUser(),oldUser.getContracts());
            boolean correct = this.insuranceSystemService.getUpdateUser().updateUser(user);

            if(correct)
                return new ResponseEntity<>(user, HttpStatus.OK);

            System.out.println("ahoj");

            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
        catch (PersonalNumberParseException e)
        {
            return new ResponseEntity<>(e.getMessage(), HttpStatus.BAD_REQUEST);
        }
        catch (PostalCodeParseException e)
        {
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }

    }
}
