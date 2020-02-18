package com.vlasov.demo_api.controller;

import com.fasterxml.jackson.databind.util.JSONPObject;
import com.vlasov.demo_api.model.*;
import com.vlasov.demo_api.repos.BalanceRepository;
import com.vlasov.demo_api.repos.HistoryRepository;
import com.vlasov.demo_api.repos.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Date;
import java.util.concurrent.atomic.AtomicLong;

@RestController
public class GreetingController {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private BalanceRepository balanceRepository;

    @Autowired
    private HistoryRepository historyRepository;

    private static String template = "Hello, %s!";
    private final AtomicLong counter = new AtomicLong();

    @GetMapping("/greeting")
    public Greeting greeting(@RequestParam(value = "name", required = false, defaultValue = "World") String name) {
        return new Greeting(counter.incrementAndGet(), String.format(template, name));
    }

    @PostMapping("/add")
    public @ResponseBody String addNewUser(@RequestBody User user){
        if (userRepository.findByUsername(user.getUsername()).isEmpty()) {
            userRepository.save(user);
            Balance balance = new Balance();

            balance.setUserid(user.getId());
            balance.setCurr1(0);
            balance.setCurr2(0);
            balance.setCurr3(0);

            balanceRepository.save(balance);

            return "Saved";
        }
        return "User is already exist!";
    }

    @GetMapping(path="/all")
    public @ResponseBody Iterable<User> getAllUsers(){
        return userRepository.findAll();
    }

    @GetMapping(path = "/user/{username}")
    public ResponseEntity<String> getUser(@PathVariable String username){
        if (!userRepository.findByUsername(username).isEmpty()) {
            User user = userRepository.getByUsername(username);
            return  new ResponseEntity<String>(
                      "Username: " + user.getUsername() +
                            "\nFirstname: " + user.getFirstname() +
                            "\nLastname: " + user.getLastname(), HttpStatus.OK);
        }
        return new ResponseEntity<String>("username " + username + "doesn't exist", HttpStatus.NOT_FOUND);

    }

    @GetMapping(path = "/user/{username}/balance")
    public ResponseEntity<String> getBalance(@PathVariable String username){
        if (!userRepository.findByUsername(username).isEmpty()) {
            User user = userRepository.getByUsername(username);
            int userId = user.getId();
            Balance balance = balanceRepository.getByUserid(userId);

            return new ResponseEntity<String>(
                    "Currency1: " + balance.getCurr1() +
                    "\nCurrency2: " +  balance.getCurr2() +
                    "\nCurrency3: " + balance.getCurr3(), HttpStatus.OK);
        }

        return new ResponseEntity<String>("username " + username + "doesn't exist", HttpStatus.NOT_FOUND);

    }

    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(@RequestBody Operations operation){

        if (!userRepository.findByUsername(operation.getFromUser()).isEmpty() && !userRepository.findByUsername(operation.getToUser()).isEmpty()){
            User userFrom = userRepository.getByUsername(operation.getFromUser());
            User userTo = userRepository.getByUsername(operation.getToUser());
            Balance balanceFrom = balanceRepository.getByUserid(userFrom.getId());
            Balance balanceTo = balanceRepository.getByUserid(userTo.getId());

            if (operation.getCurrency().equals("curr1")){
                if (balanceFrom.getCurr1() - operation.getAmount() >= 0){
                    balanceFrom.setCurr1(balanceFrom.getCurr1() - operation.getAmount());
                    balanceTo.setCurr1(balanceTo.getCurr1() + operation.getAmount());
                    balanceRepository.save(balanceFrom);
                    balanceRepository.save(balanceTo);

                    Date date = new Date();

                    History history = new History(  userFrom.getUsername(),
                                                    userTo.getUsername(),
                                                    operation.getCurrency(),
                                                    operation.getAmount(),
                                                    date);

                    historyRepository.save(history);

                    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
                    // отнимаем с балансаFrom, добавляем балансуTo, сохраняем оба баланса
                }
                else return new ResponseEntity<String>("Not enough curr1", HttpStatus.FORBIDDEN);
            }
            if (operation.getCurrency().equals("curr2")){
                if (balanceFrom.getCurr2() - operation.getAmount() >= 0) {
                    balanceFrom.setCurr2(balanceFrom.getCurr1() - operation.getAmount());
                    balanceTo.setCurr2(balanceTo.getCurr1() + operation.getAmount());
                    balanceRepository.save(balanceFrom);
                    balanceRepository.save(balanceTo);

                    Date date = new Date();

                    History history = new History(  userFrom.getUsername(),
                            userTo.getUsername(),
                            operation.getCurrency(),
                            operation.getAmount(),
                            date);

                    historyRepository.save(history);

                    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
                }
                else return new ResponseEntity<String>("Not enough curr2", HttpStatus.FORBIDDEN);
            }
            if (operation.getCurrency().equals("curr3")){
                if (balanceFrom.getCurr3() - operation.getAmount() >= 0) {
                    balanceFrom.setCurr3(balanceFrom.getCurr1() - operation.getAmount());
                    balanceTo.setCurr3(balanceTo.getCurr1() + operation.getAmount());
                    balanceRepository.save(balanceFrom);
                    balanceRepository.save(balanceTo);

                    Date date = new Date();

                    History history = new History(  userFrom.getUsername(),
                            userTo.getUsername(),
                            operation.getCurrency(),
                            operation.getAmount(),
                            date);

                    historyRepository.save(history);

                    return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
                }
                else return new ResponseEntity<String>("Not enough curr3", HttpStatus.FORBIDDEN);
            }

        }

        return new ResponseEntity<String>("One of the users doesn't exist", HttpStatus.NOT_FOUND);
    }

    @PostMapping("/user/{username}/buy")
    private ResponseEntity<String> buyMoney(@PathVariable String username,
                                            @RequestBody Operations operation){

        operation.setFromUser("MONEYKEEPER");
        operation.setToUser(username);
        return transferMoney(operation);
    }

}
