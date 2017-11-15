package org.sputnikdev.bluetooth.manager.transport.bluegiga.activator;

import gnu.io.NativeResourceException;
import gnu.io.SerialManager;
import org.osgi.framework.BundleContext;
import org.osgi.service.component.annotations.Activate;
import org.osgi.service.component.annotations.Component;
import org.sputnikdev.bluetooth.manager.transport.BluetoothObjectFactory;
import org.sputnikdev.bluetooth.manager.transport.bluegiga.BluegigaFactory;

@Component(immediate = true, name = "binding.bluetooth.transport.bluegiga.activator")
public class BlueGigaActivator {

    @Activate
    public void activate(BundleContext bundleContext) {
        try {
            SerialManager.getInstance();
            bundleContext.registerService(BluetoothObjectFactory.class.getName(), new BluegigaFactory(), null);
        } catch (NativeResourceException ex) {
            throw new IllegalStateException("Could not load bluegiga library. Bluegiga transport is not registered: "
                + ex.getMessage());
        }
    }

}
