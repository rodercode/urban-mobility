package com.example.urbanmobility.route;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import jakarta.websocket.server.PathParam;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/route")
public class RouteController {
   final RouteService routeService;

   final AccountService accountService;

    public RouteController(RouteService routeService, AccountService accountService) {
        this.routeService = routeService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<Route> getAllRoute(){
        return routeService.getAllRoutes();
    }

    @PostMapping
    public Route createRoute(@RequestBody Route route){
        return routeService.createRoute(route);
    }

    @PutMapping("/{id}")
    public Route updateRoute(@PathVariable("id") long id){
        Account account = accountService.getAccountById(id).get();
        return routeService.updateRouteById(id, account);
    }
}
