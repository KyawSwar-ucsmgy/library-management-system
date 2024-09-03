package com.spring_security_one.demo.config;

import java.io.IOException;
import java.util.Set;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configurers.AbstractHttpConfigurer;
import org.springframework.security.core.authority.AuthorityUtils;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.AuthenticationEntryPoint;
import org.springframework.security.web.SecurityFilterChain;
import org.springframework.security.web.authentication.AuthenticationFailureHandler;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;

import com.spring_security_one.demo.security.OurUserDetailService;

import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;


@Configuration
@EnableWebSecurity
public class SecurityConfig {

		@Autowired
		private OurUserDetailService userDetailService;
		
		
		public SecurityConfig(OurUserDetailService userDetailService) {
			this.userDetailService = userDetailService;
		}

		
		
		@Bean
		public static PasswordEncoder passwordEncoder() {
			
			return new BCryptPasswordEncoder();
		}
		
		@Bean 
		public SecurityFilterChain filterChain(HttpSecurity http) throws Exception {
			
			http.csrf(AbstractHttpConfigurer::disable)
			.cors(Customizer.withDefaults())
			.httpBasic(AbstractHttpConfigurer::disable)
			.authorizeHttpRequests(auth -> auth
			.requestMatchers("/auth/**").permitAll()
			.requestMatchers("/images/**").permitAll()
			.requestMatchers("/assets/**").permitAll()
			.requestMatchers("/api/public/**").permitAll()
			.requestMatchers("/static/**").permitAll()
			.requestMatchers("/user/**").hasAuthority("ROLE_USER")
			.requestMatchers("/admin/**").hasAuthority("ROLE_ADMIN")
			.requestMatchers("/staff/**").hasAuthority("ROLE_STAFF")
			.anyRequest().authenticated())
			.exceptionHandling(
					(exceptionHandling) -> exceptionHandling
					.accessDeniedPage("/accessDenied")
			)
			.formLogin(form -> form
					.loginPage("/login")
					.loginProcessingUrl("/signIn")
					.successHandler(customAuthenticationSuccessHandler())
					.failureHandler(authenticationFailureHandler())
					.permitAll()
				).logout(
						logout -> logout
						.logoutUrl("/signOut")
						.logoutSuccessUrl("/auth/login")
						.logoutSuccessHandler(
								(((request, response, authentication) -> {
									response.sendRedirect("/login");
								}))
								)
						
						.invalidateHttpSession(true)
						.permitAll()
					
			);
		
				return http.build();

		}
		
//		@Bean
//		UserDetailsService userDetailService() {
//			InMemoryUserDetailsManager manager = new InMemoryUserDetailsManager();
//			manager.createUser(User.withUsername("admin").password(passwordEncoder().encode(")));
//			
//			return new OurUserDetailService();
//		}
		
		@Bean
		OurUserDetailService userDetailService() {
			return new OurUserDetailService();
			
		}
		
		@Bean
		AuthenticationEntryPoint authenticationEntryPoint() {
			return (request, response, authException)->{
				response.sendRedirect("/login");
			};
		}
		
		
		@Bean 
		 AuthenticationProvider authenticationProvider() {
			DaoAuthenticationProvider daoAuthenticationProvider = new DaoAuthenticationProvider();
			daoAuthenticationProvider.setUserDetailsService(userDetailService);
			daoAuthenticationProvider.setPasswordEncoder(passwordEncoder());
			return daoAuthenticationProvider;
		}
		
		@Bean 
		 AuthenticationFailureHandler authenticationFailureHandler() {
			
			return (request, response, exception) -> {
				
				String errorMessage = "Incorrect username or password.";
				request.getSession().setAttribute("errorMessage", errorMessage);
				response.sendRedirect("/login?error");
			};
		}
		
		@Bean 
		public AuthenticationSuccessHandler customAuthenticationSuccessHandler() {
			
			return new AuthenticationSuccessHandler() {
				
				public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
						org.springframework.security.core.Authentication authentication)
				throws IOException, ServletException {
					Set<String> authorities = AuthorityUtils.authorityListToSet(authentication.getAuthorities());
					System.out.println("Role" + authorities);
					if (authorities.contains("ROLE_ADMIN")) {
						response.sendRedirect("/admin");
					} else if (authorities.contains("ROLE_USER")) {
						
						response.sendRedirect("/user");
					} else if (authorities.contains("ROLE_STAFF")) {
						
						response.sendRedirect("/staff");}
					
					else {
						response.sendRedirect("/home");
					}
				}
			};
		}
}
