import com.fordros.entity.Account;
import com.fordros.entity.Payments;
import com.fordros.session.AccountManager;
import com.fordros.session.AccountManagerImpl;
import com.fordros.session.PaymentManager;
import com.fordros.session.PaymentManagerImpl;
import org.junit.Test;

import java.util.List;

/**
 * Created by Fordros on 13.04.2016.
 */
public class FullCalcTest {
    AccountManager accountManager = new AccountManagerImpl();
    PaymentManager paymentManager = new PaymentManagerImpl();

    @Test
    public void fullAccTest(){
        Account wanted = accountManager.findByAccNumber("26250127222980001");
        Integer accId = wanted.getId();
        List<Payments> payments = paymentManager.findAllPaymentByAcc(accId);
        System.out.println(payments);
    }
}
