package dao;

import models.Client;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

public class ClientDAO {
    private static int nextId = 1;
    private static List<Client> clienti = new ArrayList<>();

    public void creareClient(Client client) {
        client.setId(nextId++);
        clienti.add(client);
    }

    public Client citireClient(int id) {
        for (Client client : clienti) {
            if (client.getId() == id) {
                return client;
            }
        }
        return null;
    }

    public void actualizareClient(int id, Client clientActualizat) {
        Optional<Client> clientOptional = clienti.stream()
                .filter(client -> client.getId() == id)
                .findFirst();

        clientOptional.ifPresent(client -> {
            clienti.remove(client);
            clientActualizat.setId(id);
            clienti.add(clientActualizat);
        });
    }

    public void stergereClient(Client client) {
        clienti.remove(client);
    }

    public List<Client> gasesteTotiClientii() {
        return new ArrayList<>(clienti);
    }


}
