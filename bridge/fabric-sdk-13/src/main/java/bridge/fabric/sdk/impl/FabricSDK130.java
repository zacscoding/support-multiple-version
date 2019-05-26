package bridge.fabric.sdk.impl;

import bridge.fabric.sdk.FabricSDK;
import bridge.fabric.sdk.FabricVersion;
import org.hyperledger.fabric.sdk.HFClient;

/**
 * @GitHub : https://github.com/zacscoding
 */
public class FabricSDK130 implements FabricSDK {

    @Override
    public FabricVersion getVersion() {
        return FabricVersion.V1_3;
    }

    @Override
    public String getHFClientName() {
        return HFClient.class.getName();
    }
}
