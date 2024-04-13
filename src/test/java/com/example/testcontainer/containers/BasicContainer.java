package com.example.testcontainer.containers;

import org.springframework.test.context.DynamicPropertyRegistry;
import org.springframework.test.context.DynamicPropertySource;
import org.testcontainers.containers.GenericContainer;
import org.testcontainers.containers.MySQLContainer;
import org.testcontainers.utility.DockerImageName;


public abstract class BasicContainer {

    static final String MYSQL_IMAGE = "mysql:8";
    static final MySQLContainer MY_SQL_CONTAINER;
    static {
        MY_SQL_CONTAINER = (MySQLContainer) new MySQLContainer(MYSQL_IMAGE)
                .withDatabaseName("test-database")
                .withUsername("root")
                .withPassword("password")
                .withInitScript("sql/init.sql");

        MY_SQL_CONTAINER.start();
    }

    private static final String REDIS_IMAGE = "redis:latest";
    private static final int REDIS_PORT = 6379;
    private static final GenericContainer REDIS;

    static {
        REDIS = new GenericContainer(DockerImageName.parse(REDIS_IMAGE))
                .withExposedPorts(REDIS_PORT)
                .withReuse(true);

        REDIS.start();
    }

    @DynamicPropertySource
    public static void overrideProps(DynamicPropertyRegistry registry){
        registry.add("spring.datasource.driver-class-name", MY_SQL_CONTAINER::getDriverClassName);
        registry.add("spring.datasource.url", MY_SQL_CONTAINER::getJdbcUrl);
        registry.add("spring.datasource.~username", MY_SQL_CONTAINER::getUsername);
        registry.add("spring.datasource.password", MY_SQL_CONTAINER::getPassword);

        registry.add("spring.redis.host", REDIS::getHost);
        registry.add("spring.redis.port", () -> String.valueOf(REDIS.getMappedPort(REDIS_PORT)));
    }
}