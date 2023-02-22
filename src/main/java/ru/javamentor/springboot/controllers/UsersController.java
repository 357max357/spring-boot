package ru.javamentor.springboot.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import ru.javamentor.springboot.model.User;
import ru.javamentor.springboot.service.UserService;

import javax.validation.Valid;

@Controller
public class UsersController {

    private final UserService userService;

    @Autowired
    public UsersController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/")
    public String showAllUsers(ModelMap model) {
        model.addAttribute("users", userService.getAllUsers());
        return "startPage";
    }

    @GetMapping("/{id}")
    public String getById(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "getById";
    }

    @GetMapping("/new")
    public String createUser(@ModelAttribute("user") User user) {
        return "newUser";
    }

    @PostMapping()
    public String addNewUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "newUser";

        userService.addUser(user);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String editUser(@PathVariable("id") long id, ModelMap model) {
        model.addAttribute("user", userService.getById(id));
        return "editUser";
    }

    @PatchMapping("/{id}")
    public String updateUser(@ModelAttribute("user") @Valid User user,
                             BindingResult bindingResult) {

        if (bindingResult.hasErrors())
            return "/editUser";

        userService.updateUser(user);
        return "redirect:/";
    }

    @DeleteMapping("/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.deleteUser(userService.getById(id));
        return "redirect:/";
    }

}
