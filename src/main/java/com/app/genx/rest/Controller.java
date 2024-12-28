package com.app.genx.rest;


import com.app.genx.service.DataProcessor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@Slf4j
@RestController
@RequestMapping("/v1-genx")
public class Controller {

    @GetMapping("/")
    public ResponseEntity <Map<String, String>> getStatus (){
        final Map<String, String> dump = DataProcessor.processData();
        return new ResponseEntity<> (dump, HttpStatus.OK);
    }
    @GetMapping("/info")
    public Map<String, String> getNodeInfo() {
        log.info("Fetching node info...");

        Map<String, String> nodeInfo = new HashMap<>();
        nodeInfo.put("node", System.getenv("NODE_NAME") != null ? System.getenv("NODE_NAME") : "unknown");
        nodeInfo.put("namespace", System.getenv("NAMESPACE") != null ? System.getenv("NAMESPACE") : "unknown");
        nodeInfo.put("pod", System.getenv("POD_NAME") != null ? System.getenv("POD_NAME") : "unknown");

        log.info("Node info retrieved: {}", nodeInfo);
        return nodeInfo;
    }
}
