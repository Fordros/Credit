import com.fordros.entity.CreditDebt;
import org.junit.Test;

import java.util.Date;

/**
 * Created by Fordros on 13.04.2016.
 */
public class CreditDebtTest {
    private CreditDebt creditDebt = new CreditDebt();

    @Test
    public void calcAllTest(){



    }



    @Test
    public void calcDebtsTest() {
        int result = creditDebt.calcDebts(-126671,156000,0);
        System.out.println(result + "коп.");


    }

    @Test
    public void calcPercentPrincipalDebtTest() {
        int result = creditDebt.calcPercentPrincipalDebt(29329,156000,18);
        System.out.println(result + "коп.");


    }

    @Test
    public void calcPercentPastDueDebtsTest() {
        int result = creditDebt.calcPercentPastDueDebts(29329,60);
        System.out.println(result + "коп.");


    }

    @Test
    public void isBillingDateTest() {
        Date date = new Date();
        boolean result = creditDebt.isBillingDate(date);
        System.out.println(result);


    }




}
