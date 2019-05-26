package server;

import bridge.fabric.sdk.FabricSDK;
import bridge.fabric.sdk.FabricVersion;
import bridge.fabric.sdk.impl.FabricSDK130;
import bridge.fabric.sdk.impl.FabricSDK140;

/**
 * @GitHub : https://github.com/zacscoding
 */
public class FabricSdkFactory {

    public static FabricSDK newInstance(FabricVersion version) {
        if (version == null) {
            throw new IllegalArgumentException("FabricVersion must be not null");
        }

        switch (version) {
            case V1_3:
                return new FabricSDK130();
            case V1_4:
                return new FabricSDK140();
            default:
                throw new UnsupportedOperationException("Not supported fabric version : " + version);
        }
    }
}