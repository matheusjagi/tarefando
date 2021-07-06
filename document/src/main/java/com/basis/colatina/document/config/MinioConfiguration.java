package com.basis.colatina.document.config;

import io.minio.BucketExistsArgs;
import io.minio.MakeBucketArgs;
import io.minio.MinioClient;
import lombok.RequiredArgsConstructor;
import lombok.SneakyThrows;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
@RequiredArgsConstructor
public class MinioConfiguration {

    private final ApplicationProperties applicationProperties;

    @Bean
    @SneakyThrows
    public MinioClient minioClient() {
        MinioClient minioClient = MinioClient.builder()
                .endpoint(applicationProperties.getMinio().getUrl())
                .credentials(applicationProperties.getMinio().getUsername(), applicationProperties.getMinio().getPassword())
                .build();

        if(!isBucket(minioClient)){
            createBucket(minioClient);
        }

        return minioClient;
    }

    @SneakyThrows
    private boolean isBucket(MinioClient minioClient) {
        return minioClient.bucketExists(BucketExistsArgs.builder().bucket(applicationProperties.getMinio().getBucker()).build());
    }

    @SneakyThrows
    private void createBucket(MinioClient minioClient) {
        minioClient.makeBucket(MakeBucketArgs.builder().bucket(applicationProperties.getMinio().getBucker()).build());
    }
}
