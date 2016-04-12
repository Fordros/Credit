package com.fordros.session;


import com.fordros.entity.Account;
import java.util.List;
/**
 * Created by Fordros on 22.03.2016.
 */
public interface AccountManager {
    public Account findByAccNumber(String accNumber);

    public List<Account> loadAllAcc();

    public void saveNewAcc(Account Account);

    public Account findAccById(Integer id);

    public void deleteAcc(Account Account);
}
