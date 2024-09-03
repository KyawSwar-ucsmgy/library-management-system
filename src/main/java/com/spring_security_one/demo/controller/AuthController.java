package com.spring_security_one.demo.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/login")
public class AuthController {

	@GetMapping({"", "/"})
    //@GetMapping("/auth/login")
	public String loginPage() {
		return "login";
	}
	
//  @GetMapping("/admin/dashboard")
//    public String adminDashboard() {
//        return "admin_dashboard"; // Example endpoint after successful login for admin role
//    }
//    
//    @GetMapping("/user/home")
//    public String userHome() {
//        return "user_home"; // Example endpoint after successful login for user role
//    }
//    
//    @GetMapping("/home")
//    public String home() {
//        return "home"; // Example endpoint after successful login for other roles
//    }

}
