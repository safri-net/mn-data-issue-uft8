# Micronaut Data fails with MariaDb with charset utf8mb4

After upgrading from micronaut 1.3.7 to 2.1.4 we get the following error `Error executing SQL UPDATE: (conn=18) Invalid 
utf8mb4 character string: 'ACED00'` when updating an entity.  

**Table definition:**
```sql
CREATE TABLE `test_entity`
(
    `id`       varchar(36) NOT NULL,
    `name`     varchar(30) NOT NULL,
    `created`  datetime    NOT NULL,
    `modified` datetime    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB
  DEFAULT CHARSET = utf8mb4
  COLLATE = utf8mb4_unicode_ci;
```

Everythings works fine, as soon as we change the table definition to:

```sql
CREATE TABLE `test_entity`
(
    `id`       varchar(36) NOT NULL,
    `name`     varchar(30) NOT NULL,
    `created`  datetime    NOT NULL,
    `modified` datetime    NOT NULL,
    PRIMARY KEY (`id`)
) ENGINE = InnoDB;
```

To reproduce the error run `./gradlew test`. The `WorkingSpec` class uses a flyway migration without uft8 settings and
the `FailingSpec` class uses a flyway migration with uft8 settings.

