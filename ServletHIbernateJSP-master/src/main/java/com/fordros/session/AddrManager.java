package com.fordros.session;

import com.fordros.entity.Addr;
import java.util.List;

/**
 * Created by Fordros on 25.03.2016.
 */
public interface AddrManager {

    public List<Addr> loadAllAddr();

    public void saveNewAddr(Addr addr);

    public void deleteAddr(Addr addr);
}
