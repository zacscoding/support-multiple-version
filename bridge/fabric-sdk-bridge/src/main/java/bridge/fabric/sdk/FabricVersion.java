package bridge.fabric.sdk;

import java.util.EnumSet;
import java.util.Set;

/**
 * @GitHub : https://github.com/zacscoding
 */
public enum FabricVersion {
    V1_3, V1_4, UNKNOWN;

    private static Set<FabricVersion> VERSION_SET = EnumSet.allOf(FabricVersion.class);

    public static FabricVersion getVersion(String version) {
        for (FabricVersion fabricVersion : VERSION_SET) {
            if (fabricVersion.name().equalsIgnoreCase(version)) {
                return fabricVersion;
            }
        }

        return UNKNOWN;
    }
}
