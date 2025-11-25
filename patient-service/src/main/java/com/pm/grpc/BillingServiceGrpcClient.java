package com.pm.grpc;

import billing.BillingRequest;
import billing.BillingResponse;
import billing.BillingServiceGrpc;
import io.grpc.ManagedChannel;
import io.grpc.ManagedChannelBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.beans.factory.annotation.Value;

@Service
public class BillingServiceGrpcClient {
    private static final Logger log = LoggerFactory.getLogger(BillingServiceGrpcClient.class);
//    private final BillingServiceGrpc.BillingServiceBlockingStub blockingStub;

    //localhost:9001/BillingService/CreatePatientAccount
    //aws.grps:123123/BillingService/CreatePatientAccount
//    public BillingServiceGrpcClient(
//            @Value("${billing.service.address.localhost}") String serverAddress,
//            @Value("${billing.service.grpc.port:9001}") int serverPort
//
//    ) {
//        log.info("Connecting to billing service GRPC service at {}:{}", serverAddress, serverPort);
//
//        ManagedChannel channel = ManagedChannelBuilder.forAddress(serverAddress,
//                serverPort).usePlaintext().build();
//
//        blockingStub = BillingServiceGrpc.newBlockingStub(channel);
//    }

    public BillingResponse createBillingAccount(String patientId,
                                            String name, String email) {
        BillingRequest request = BillingRequest.newBuilder().setPatientId(patientId)
                .setName(name).setEmail(email).build();
//        BillingResponse response = blockingStub.createBillingAccount(request);
        BillingResponse response = BillingResponse.newBuilder().setAccountId("dummy1243").setStatus("ACTIVE").build();
        log.info("Received response from Billing service via GRPC: {}", response);
        return response;
    }
}
