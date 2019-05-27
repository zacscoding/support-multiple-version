package bridge.fabric.sdk.impl;

import bridge.fabric.sdk.FabricSDK;
import bridge.fabric.sdk.FabricVersion;
import org.hyperledger.fabric.sdk.SDKUtils;

/**
 * Fabric SDK impl by using fabric sdk 1.0.0
 */
public class FabricSDK100 implements FabricSDK {

    @Override
    public FabricVersion getVersion() {
        return FabricVersion.V1_0;
    }

    @Override
    public String callDifferentMethods() {
        try {
            displayDifferentMethods(SDKUtils.class, "calculateBlockHash");
            // long blockNumber, byte[] previousHash, byte[] dataHash
            SDKUtils.calculateBlockHash(
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
