package bridge.fabric.sdk.impl;

import bridge.fabric.sdk.FabricSDK;
import bridge.fabric.sdk.FabricVersion;
import org.hyperledger.fabric.sdk.HFClient;

/**
 * Fabric sdk 1.4.0
 *
 * @GitHub : https://github.com/zacscoding
 */
public class FabricSDK140 implements FabricSDK {

    @Override
    public FabricVersion getVersion() {
        return FabricVersion.V1_4;
    }

    @Override
    public String getHFClientName() {
        return HFClient.class.getName();
    }
}
