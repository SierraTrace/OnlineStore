package DAO;

import java.util.List;
import java.util.Optional;

// TODO, pendiente de revisar e implementar.

public abstract class PedidoDAO implements IDao {
    @Override
    public Optional get(long id) {
        return Optional.empty();
    }

    @Override
    public List getAll() {
        return List.of();
    }

    @Override
    public void save(Object o) {

    }

    @Override
    public void update(Object o) {

    }

    @Override
    public void delete(Object o) {

    }
}
