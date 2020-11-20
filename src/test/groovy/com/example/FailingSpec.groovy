package com.example

import io.micronaut.context.annotation.Property
import io.micronaut.test.extensions.spock.annotation.MicronautTest
import io.micronaut.test.support.TestPropertyProvider
import org.testcontainers.containers.MariaDBContainer
import spock.lang.AutoCleanup
import spock.lang.Shared
import spock.lang.Specification

import javax.inject.Inject

@MicronautTest(transactional = false)
@Property(name = "migration.dir", value = "utf8")
class FailingSpec extends Specification implements TestPropertyProvider {

    @AutoCleanup
    static MariaDBContainer mariaDB

    static {
        mariaDB = new MariaDBContainer().tap {
            withUsername("root")
            withPassword("")
            withDatabaseName("test")
            withUrlParam("dumpQueriesOnException", "true")
            start()
        }
    }

    @Inject
    @Shared
    DefaultRepository repository

    def "test"() {
        when:
        def e = repository.save(
                new TestEntity().tap {
                    name = "test"
                }
        )
        then:
        e.id
        e.name == "test"

        when:
        e.name = "update"
        e = repository.update(e)

        then:
        e.name == "update"
    }

    @Override
    Map<String, String> getProperties() {
        [
                "db.port": "${mariaDB.getMappedPort(3306)}"
        ]
    }

}
