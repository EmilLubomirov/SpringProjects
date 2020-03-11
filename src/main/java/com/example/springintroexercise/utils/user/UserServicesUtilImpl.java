package com.example.springintroexercise.utils.user;

import com.example.springintroexercise.data.entities.ClientsService;
import com.example.springintroexercise.data.entities.User;
import com.example.springintroexercise.data.repositories.ClientsServiceRepository;
import com.example.springintroexercise.data.repositories.UserRepository;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class UserServicesUtilImpl implements UserServicesUtil
{
    private final ClientsServiceRepository clientsServiceRepository;
    private final UserRepository userRepository;

    public UserServicesUtilImpl(ClientsServiceRepository clientsServiceRepository, UserRepository userRepository) {
        this.clientsServiceRepository = clientsServiceRepository;
        this.userRepository = userRepository;
    }


    @Override
    public Map<String, String> getAllWithNonNullableAmount(String data)
    {
        String[] allServices = data.split("!@");

        Map<String, String> namesWithAmount = new HashMap<>();

        for (String service : allServices)
        {
            String[] separate = service.split("-");

            String name = separate[0];
            String amount = separate[1];

            if (!amount.equals("0"))
            {
                namesWithAmount.putIfAbsent(name, amount);
            }
        }

        return namesWithAmount;
    }

    @Override
    public void addUserToServices(Map<String, String> services, String userUsername)
    {
        for (Map.Entry<String, String> service : services.entrySet())
        {
            String serviceDescription = service.getKey();
            String serviceAmount = service.getValue();

            ClientsService clientsService = clientsServiceRepository.getByDescription(serviceDescription);

            if (clientsService != null)
            {
                User user = userRepository.findByUsername(userUsername);
                clientsService.getUsers().add(user);
                clientsServiceRepository.save(clientsService);
            }

        }
    }
}
