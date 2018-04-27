package ru.myori.repository.reports;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.OrderProduct;
import ru.myori.model.SummaryOrder;

import java.util.List;


//@Repository
public class JpaSummaryOrderRepositoryImpl implements SummaryOrderRepository{

/*    @PersistenceContext
    private EntityManager em;


    @Override
    public List<SummaryOrder> getAll() {
        return em.createNamedQuery(SummaryOrder.GET_SUMMARY, SummaryOrder.class)
                .getResultList();
    }*/

    @Autowired
    CrudSummaryOrderRepository crudSummaryOrderRepository;

    public List<SummaryOrder> getAll(){
        return crudSummaryOrderRepository.getAll();
    }

}