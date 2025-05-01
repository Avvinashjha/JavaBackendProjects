# How do Make a user and password?

Previously what we were doing is we are just getting the password from logs, and we have a user "user". 

Now we are going to create a user and password for the user.
- Go to you `application.properties` file and add the following lines:

```
spring.security.user.name=admin
spring.security.user.password=admin@123
```

Now We will be able to authenticate with the user and password we created. But this is not a good practice to hard code the password in the application.properties file.

# How Can we create Multiple User and Password?

We have to do some configuration such that Spring SecurityFilterChain follows our custom configuration.

Let's see the example

```java
@Configuration
@EnableWebSecurity
public class WebSecurityConfig {

    // As SecurityFilterChain is already defined, but we are taking control back and we will define owr own SecurityFilterChain
    @Bean
    public SecurityFilterChain securityFilterChain(HttpSecurity httpSecurity) throws Exception {
        httpSecurity
                .csrf(csrf -> csrf.disable())
                .authorizeHttpRequests(
                request -> request
                         .anyRequest().authenticated()
                )
                .formLogin(Customizer.withDefaults())
                .httpBasic(Customizer.withDefaults());
        return httpSecurity.build();
    }

    @Bean
    public UserDetailsService userDetailsService(){
        UserDetails avinash = User.withUsername("avinash")
                .password("{noop}test123")
                .roles("USER")
                .build();
        UserDetails sam = User.withUsername("sam")
                .password("{noop}test123")
                .roles("USER")
                .build();
        return new InMemoryUserDetailsManager(avinash, sam);
    }
}
```

- In the above code we are creating two users `avinash` and `sam` with password `test123`.
- We are using `InMemoryUserDetailsManager` to create the users in memory.
- We are using `User.withUsername` to create the user and `password("{noop}test123")` to set the password. The `{noop}` is used to indicate that the password is not encoded.
- We are using `roles("USER")` to set the role of the user. You can also set multiple roles by using `roles("USER", "ADMIN")`.
- We are using `@EnableWebSecurity` to enable the Spring Security.
- We are using `@Configuration` to indicate that this is a configuration class.
- We are using `@Bean` to indicate that this is a bean and it will be managed by Spring.
- We are using `SecurityFilterChain` to define the security filter chain.
- We are using `HttpSecurity` to configure the security.
- We are using `csrf(csrf -> csrf.disable())` to disable the CSRF protection. You can also enable it by removing this line.
- We are using `authorizeHttpRequests` to authorize the requests.
- We are using `request -> request.anyRequest().authenticated()` to authorize all requests. You can also specify the URL patterns to authorize.
- We are using `formLogin(Customizer.withDefaults())` to enable the form login. You can also customize the login page by using `formLogin().loginPage("/login")`.
- We are using `httpBasic(Customizer.withDefaults())` to enable the basic authentication. You can also customize the login page by using `httpBasic().realmName("My Realm")`.
- We are using `return httpSecurity.build()` to build the security filter chain.
