package Repositories;

import Models.Gate;

import java.util.Map;
import java.util.Optional;
import java.util.TreeMap;

public class GateRepository {
    private Map<Long,Gate> gates = new TreeMap<>();
    public Optional<Gate> findById(Long id){
        if(gates.containsKey(id)){
            return Optional.ofNullable(gates.get(id));
        }
        return Optional.empty();
    }
}
