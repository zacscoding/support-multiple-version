package server;

import bridge.fabric.sdk.FabricSDK;
import bridge.fabric.sdk.FabricVersion;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

/**
 * @GitHub : https://github.com/zacscoding
 */
@Slf4j
@RestController
@SpringBootApplication
public class ServerApplication {

    public static void main(String[] args) {
        SpringApplication.run(ServerApplication.class, args);
    }

    @GetMapping("/check/{version}")
    public ResponseEntity checkFabricSdk(@PathVariable("version") String version) {
        try {
            FabricVersion fabricVersion = FabricVersion.getVersion(version);
            FabricSDK fabricSDK = FabricSdkFactory.newInstance(fabricVersion);
            return ResponseEntity.ok(fabricSDK.getVersion());
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.badRequest().body("Invalid version : " + version);
        }
    }

    @GetMapping("/client/{version}")
    public ResponseEntity checkFabricClient(@PathVariable("version") String version) {
        try {
            FabricVersion fabricVersion = FabricVersion.getVersion(version);
            FabricSDK fabricSDK = FabricSdkFactory.newInstance(fabricVersion);
            return ResponseEntity.ok(fabricSDK.getHFClientName());
        } catch (UnsupportedOperationException e) {
            return ResponseEntity.badRequest().body("Invalid version : " + version);
        }
    }
}
