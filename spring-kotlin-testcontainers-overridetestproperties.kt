@Testcontainers
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@ContextConfiguration(initializers = [ESIntegrationTest.Initializer::class])
class ESIntegrationTest {
    companion object {
        private const val TEST_FILE = "src/test/resources/integration-test.yml"

        private val env = CustomDockerComposeContainer(TEST_FILE)

        @Container
        @ClassRule
        private val redisContainer = env
            .withExposedService("sc_redis_1", 6379,
                Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(30)))

        @Container
        @ClassRule
        private val quContainer = env
            .withExposedService("query_understanding_1", 50060,
                Wait.forListeningPort().withStartupTimeout(Duration.ofSeconds(120)))
    }
    
    // Test properties are ovverriden during initialization of the test
    class Initializer : ApplicationContextInitializer<ConfigurableApplicationContext> {
        override fun initialize(configurableApplicationContext: ConfigurableApplicationContext) {
            val values = TestPropertyValues.of(
                "spring.elasticsearch.urls[0]=" + "http://" + elasticsearchContainer.httpHostAddress
            )
            values.applyTo(configurableApplicationContext)
        }
    }
}

