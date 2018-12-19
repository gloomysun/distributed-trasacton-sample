package common.pojo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.mengyun.tcctransaction.api.TransactionContext;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class TransactionEntity<T> {

    private TransactionContext transactionContext;

    private T body;

}
