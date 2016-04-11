import com.fordros.entity.Client;
import com.fordros.session.ClientManager;
import com.fordros.session.ClientManagerImpl;
import org.junit.Test;

/**
 * Created by Fordros on 15.03.2016.
 */
public class ClientServiceTest {

    @Test
    public void findByNameClientTest() throws Exception {
        ClientManager clientManager = new ClientManagerImpl();
        Client wanted = clientManager.findByClientName("Ростислав", "Иващенко");

    }
}



