package web.PP_3_1_2.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import web.PP_3_1_2.model.User;
import web.PP_3_1_2.service.UserService;

@Controller
@RequestMapping("/users")
public class UserController {

    private final UserService userService;

    @Autowired
    public UserController(UserService userService) {

        this.userService = userService;
    }


    @GetMapping()
    public String index(Model model, @RequestParam(value = "id", defaultValue = "0") int id) {
        if (id == 0) {
            model.addAttribute("list", userService.showAllUsers());
        } else {
            model.addAttribute("list", userService.showUser(id));
        }
        return "/index";
    }

    @GetMapping("/new")
    public String newUser(@ModelAttribute(value = "user") User user) {
        return "/new";
    }

    @PostMapping()
    public String create(@ModelAttribute("user") User user) {
        userService.saveUser(user);
        return "redirect:/users";
    }

    @GetMapping("/delete")
    public String delete(@RequestParam("id") int id) {
        userService.deleteUser(id);
        return "redirect:/users";
    }

    @GetMapping("/update")
    public String update(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.showUser(id));
        return "/update";
    }

    @PostMapping("/update")
    public String userupdate(@ModelAttribute("user") User user) {
        userService.updateUser(user);
        return "redirect:/users";
    }

    @GetMapping("/show")
    public String show(Model model, @RequestParam("id") int id) {
        model.addAttribute("user", userService.showUser(id));
        return "/show";
    }
}
