package ru.myori.repository.reports;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;
import ru.myori.model.SummaryOrder;

import java.util.List;

@Transactional(readOnly = true)
public interface CrudSummaryOrderRepository extends JpaRepository<SummaryOrder, Integer> {

/*    @Query("SELECT " +
            "    new com.path.to.class.SurveyAnswerStatistics(v.answer, count(v)) " +
            "FROM " +
            "    Survey v " +
            "GROUP BY " +
            "    v.answer")*/

    @Query("SELECT new SummaryOrder(op.product, SUM(op.volume)) FROM OrderProduct op GROUP BY op.product")
    List<SummaryOrder> getAll();
}
