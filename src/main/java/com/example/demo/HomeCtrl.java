package com.example.demo;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

public class HomeCtrl {


    @Autowired
    AccountRepository accountRepository;
    @Autowired
    UserRepository userRepository;

    @Autowired
    private LoginService loginservice;


    @RequestMapping("/home")
    public String homepage(Model model) {
        model.addAttribute("accountBalance", accountRepository.findAll());
        model.addAttribute("login", userRepository.findAll());
        return "index";
        @RequestMapping("/")
        public String index(Model model) {
            return "loginform";
        }

        @RequestMapping(value = "/user",method = RequestMethod.GET)
        public String getLoginForm(){
            return "loginform";
        }

        @RequestMapping(value = "/login",method = RequestMethod.POST)
        public String doLogin(@ModelAttribute(name = "loginForm") User user, Model model)
        {

            if(service.findUser(user.getUsername(),user.getPassword())!= null)
            {
                return "home";
            }
            else
            {
                model.addAttribute("invalidCredentials",true);
                return "loginform";
            }
        }

        @RequestMapping(value = "/register",method = RequestMethod.GET)
        public String getRegisterForm(Model model)
        {
            model.addAttribute("login",new Login());

            return "registerform";
        }

        @RequestMapping(value = "/register",method = RequestMethod.POST)
        public String register(@Valid User userForm, BindingResult result){

            if(result.hasErrors()){
                return "registerform";
            }

            String firstname = userForm.getFirstname();
            String lastname = userForm.getLastname();
            String username = userForm.getUsername();
            String password = userForm.getPassword();

            User = new User(firstname,lastname,username,password);

            service.registerUser(login);
            return "loginform";
        }

    }
    @RequestMapping("/accountlist")
    public String departmentList(Model model) {
        model.addAttribute("accountBalances", accountRepository.findAll());
        return "accountlist";

    }

    @GetMapping("/addbalance")
    public String personForm(Model model) {
        model.addAttribute("account", new Account());
        return "balanceform";
    }

    @PostMapping("/procesbalance")
    public String processForm1(@Valid Account account,
                               BindingResult result) {
        if (result.hasErrors()) {
            return "balanceform";
        }
        accountRepository.save(account);
        return "redirect:/accountlist";
    }
    @RequestMapping("/logintlist")
    public String employeeList(Model model){
        model.addAttribute("logins", userRepository.findAll());
        return "loginlist";
    }

    @GetMapping("/addlogin")
    public String employeeForm(Model model){
        model.addAttribute("user", new User());
        model.addAttribute("account", accountRepository.findAll());
        return "loginform";
    }
    @PostMapping("/proceslogin")
    public String processForm2(@Valid User user,
                               BindingResult result){
        if (result.hasErrors()){
            return "loginform";
        }
        userRepository.save(user);
        return "redirect:/loginlist";
    }
    @RequestMapping("/detailperson/{id}")
    public String showPerson(@PathVariable("id") long id, Model model)
    {model.addAttribute("accountbalance", accountRepository.findAll());
        return "showbalance";
    }
    @RequestMapping("/updatebalance/{id}")
    public String updatePerson(@PathVariable("id") long id,Model model){
        model.addAttribute("accountbalance", accountRepository.findById(id).get());
        return "balanceform";
    }
    @RequestMapping("/deletebalance/{id}")
    public String delPerson(@PathVariable("id") long id){
        accountRepository.deleteById(id);
        return "redirect:/";
    }
    @RequestMapping("/detaillogin/{id}")
    public String showPet(@PathVariable("id") long id, Model model)
    {model.addAttribute("login", userRepository.findById(id).get());
        return "showpet";
    }
    @RequestMapping("/updatelogin/{id}")
    public String updatePet(@PathVariable("id") long id,Model model){
        model.addAttribute("accountbalance", accountRepository.findById(id).get());
        model.addAttribute("logins", userRepository.findAll());
        return "loginform";
    }
    @RequestMapping("/deletelogin/{id}")
    public String delPet(@PathVariable("id") long id){
        userRepository.deleteById(id);
        return "redirect:/";
    }
}
