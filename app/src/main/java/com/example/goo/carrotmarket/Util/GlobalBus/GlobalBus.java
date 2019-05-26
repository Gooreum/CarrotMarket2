package com.example.goo.carrotmarket.Util.GlobalBus;

import com.squareup.otto.Bus;

/**
 * Created by Goo on 2019-05-15.
 */

public class GlobalBus {
    private static Bus sBus;

    public static Bus getBus() {
        if (sBus == null)
            sBus = new Bus();
        return sBus;
    }
}