package com.win.sfl;

import com.win.sfl.pages.BasePageClassFactory;

public class FactoryManager {

    private static ThreadLocal<BasePageClassFactory> pageClassFactoryThreadLocal = new ThreadLocal<>();

    private FactoryManager() {
    }

    public static BasePageClassFactory getPageClassFactory() {
        return pageClassFactoryThreadLocal.get();
    }

    public static void registerPageClassFactory(BasePageClassFactory pageClassFactory) {
        pageClassFactoryThreadLocal.set(pageClassFactory);
    }

    public static void removePageClassFactory() {
        pageClassFactoryThreadLocal.remove();
    }

}
