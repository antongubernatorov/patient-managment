package ru.gubern.billingservice.grpc;

import billing.BillingRequest;
import billing.BullingResponse;
import io.grpc.stub.StreamObserver;
import lombok.extern.slf4j.Slf4j;
import net.devh.boot.grpc.server.service.GrpcService;

import static billing.BillingServiceGrpc.BillingServiceImplBase;

@Slf4j
@GrpcService
public class BillingGrpcService extends BillingServiceImplBase{

    @Override
    public void createBillingAccount(BillingRequest request, StreamObserver<BullingResponse> responseObserver) {
        log.info("CreateBillingAccount request received {}", request.toString());

        BullingResponse response = BullingResponse.newBuilder()
                .setAccountId(request.getPatientId())
                .setStatus("ACTIVE")
                .build();

        responseObserver.onNext(response);
        responseObserver.onCompleted();
    }
}
