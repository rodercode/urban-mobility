package com.example.urbanmobility.route;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class RouteService {

    private RouteRepository routeRepository;

    public RouteService(RouteRepository routeRepository) {
        this.routeRepository = routeRepository;
    }

    public Route createRoute(Route route){
        return routeRepository.save(route);
    }

    public List<Route> getAllRoutes(){
        return routeRepository.findAll();
    }

    public Route updateRouteById(Long id, Account account) {
        Route route = routeRepository.findById(id).get();
        route.setAccount(account);
        return routeRepository.save(route);
    }
}
