package bridge.fabric.sdk.impl;

import bridge.fabric.sdk.FabricSDK;
import bridge.fabric.sdk.FabricVersion;
import org.hyperledger.fabric.sdk.SDKUtils;

/**
 * Fabric SDK impl by using fabric sdk 1.4.0
 */
public class FabricSDK140 implements FabricSDK {

    @Override
    public FabricVersion getVersion() {
        return FabricVersion.V1_4;
    }

    @Override
    public String callDifferentMethods() {
        try {
            displayDifferentMethods(SDKUtils.class, "calculateBlockHash");
            SDKUtils.calculateBlockHash(
                null,
                1L,
                new byte[0],
                new byte[0]
            );
            return null;
        } catch (Exception e) {
            return e.getMessage();
        }
    }
}
