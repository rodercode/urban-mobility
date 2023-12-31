package com.example.urbanmobility.transportation;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path = "api/route")
public class TransportController {

    final TransportService routeService;

   final AccountService accountService;

    public TransportController(TransportService routeService, AccountService accountService) {
        this.routeService = routeService;
        this.accountService = accountService;
    }

    @GetMapping
    public List<Transport> getAllRoute(){
        return routeService.getAllRoutes();
    }

    @PostMapping("account/{accountId}")
    public Transport createRoute(@RequestBody Transport route, @PathVariable("accountId") long accountId){
        return routeService.createTransport(route,accountId);
    }

    @PutMapping("/{transportId}/account/{accountId}")
    public Transport updateRoute(
            @PathVariable("transportId") long transportId,
            @PathVariable("accountId") long accountId,
            @RequestBody Transport transport
    ){
        return routeService.updateRouteById(transportId, transport,accountId);
    }
}
