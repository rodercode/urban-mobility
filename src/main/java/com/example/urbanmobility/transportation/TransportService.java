package com.example.urbanmobility.transportation;

import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.exception.ResourceNotFoundException;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Objects;

@Service
public class TransportService {
    private AccountService accountService;
    private TransportRepository transportRepository;

    public TransportService(AccountService accountService, TransportRepository transportRepository) {
        this.accountService = accountService;
        this.transportRepository = transportRepository;
    }



    public Transport createRoute(Transport transport, long accountId){
        return transportRepository.save(transport);
    }

    public List<Transport> getAllRoutes(){
        return transportRepository.findAll();
    }

    // Update route
    public Transport updateRouteById(Long id, Transport transport){
        transport.setId(id);
        return transportRepository.save(transport);
    }

    // Assigne route to user
    public Transport updateRouteById(long transportId, long accountId) {
        Transport fetchedTransport = transportRepository.findById(transportId).get();
        Account account = accountService.getAccountById(accountId).get();
        fetchedTransport.setAccount(account);
        fetchedTransport.setId(transportId);
        return transportRepository.save(fetchedTransport);
    }

    public Transport updateRouteById(long transportId) {
        Transport fetchedTransport = transportRepository.findById(transportId).get();
        fetchedTransport.setAccount(null);
        fetchedTransport.setId(transportId);
        return transportRepository.save(fetchedTransport);
    }
}
