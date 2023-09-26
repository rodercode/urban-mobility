package com.example.urbanmobility.transportation;
import com.example.urbanmobility.account.Account;
import com.example.urbanmobility.account.AccountService;
import com.example.urbanmobility.auth.AuthService;
import com.example.urbanmobility.exception.InvalidInputException;
import org.springframework.stereotype.Service;
import java.util.List;

@Service
public class TransportService {
    private AccountService accountService;
    private TransportRepository transportRepository;
    private AuthService authService;

    public TransportService(AccountService accountService, TransportRepository transportRepository, AuthService authService) {
        this.accountService = accountService;
        this.transportRepository = transportRepository;
        this.authService = authService;
    }

    public Transport createTransport(Transport transport, long accountId){
        authService.validSupplier(accountId);
        if(transport == null){
            throw new InvalidInputException("Route cannot be null!");
        }

        return transportRepository.save(transport);
    }

    public List<Transport> getAllRoutes(){
        return transportRepository.findAll();
    }

    // Update route
    public Transport updateRouteById(Long id, Transport transport,long accountId){
        authService.validSupplier(accountId);
        supplierValidForChangeRoute(accountId,transport);
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

    public String supplierValidForChangeRoute(long accountId,Transport transport){
        Account supplier = accountService.getAccountById(accountId).get();
        if (!supplier.getUsername().equals(transport.getSupplier())){
            throw new InvalidInputException("You are not the supplier of this route!");
        }
        return "You are the supplier of this route!";
    }


}
