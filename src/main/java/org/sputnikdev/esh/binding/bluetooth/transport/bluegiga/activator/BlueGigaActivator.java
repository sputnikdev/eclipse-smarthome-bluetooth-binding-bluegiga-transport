package org.sputnikdev.esh.binding.bluetooth.transport.bluegiga.activator;

import gnu.io.NativeResourceException;
import gnu.io.SerialManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.osgi.service.component.annotations.Deactivate;
import org.sputnikdev.bluetooth.manager.transport.BluetoothObjectFactory;
import org.sputnikdev.bluetooth.manager.transport.bluegiga.BluegigaFactory;

@Component(immediate = true, name = "binding.bluetooth.transport.bluegiga.activator")
public class BlueGigaActivator {

    private BluegigaFactory bluegigaFactory;

    @Activate
    public void activate(BundleContext bundleContext) {
        try {
            SerialManager.getInstance();
            // preload native classes
            bluegigaFactory = new BluegigaFactory();
            bundleContext.registerService(BluetoothObjectFactory.class.getName(), bluegigaFactory, null);
        } catch (NativeResourceException ex) {
            throw new IllegalStateException("Could not load bluegiga library. Bluegiga transport is not registered: "
                + ex.getMessage());
        }
    }

    @Deactivate
    public void deactivate(BundleContext bundleContext) {
        if (bluegigaFactory != null) {
            bluegigaFactory.dispose();
            bluegigaFactory = null;
        }
    }

}
