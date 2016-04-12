import com.fordros.entity.Account;
import com.fordros.session.AccountManager;
import com.fordros.session.AccountManagerImpl;
import org.junit.Test;

/**
 * Created by Fordros on 15.03.2016.
 */
public class AccountServiceTest {

    @Test
    public void findByAccTest() throws Exception {
        AccountManager accountManager = new AccountManagerImpl();
        Account wanted = accountManager.findByAccNumber("26250116102980001");
        System.out.println(wanted);
    }
}



