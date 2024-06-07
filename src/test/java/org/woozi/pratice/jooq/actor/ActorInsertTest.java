package org.woozi.pratice.jooq.actor;

import org.jooq.generated.tables.pojos.Actor;
import org.jooq.generated.tables.records.ActorRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class ActorInsertTest {

    @Autowired
    ActorRepository actorRepository;

    @Test
    @DisplayName("자동생성된 DAO를 통한 insert")
    @Transactional
    void insert_dao() {

        // given
        var actor = new Actor();
        actor.setFirstName("John");
        actor.setLastName("Doe");
        actor.setLastUpdate(LocalDateTime.now());

        // when
        actorRepository.saveByDao(actor);

        // then
        assertThat(actor.getActorId()).isNotNull();
    }

    @Test
    @DisplayName("ActiveRecord 를 통한 insert")
    @Transactional
    void insert_by_record() {

        // given
        var actor = new Actor();
        actor.setFirstName("John");
        actor.setLastName("Doe");
//        actor.setLastUpdate(LocalDateTime.now());

        // when
        ActorRecord newActorRecord = actorRepository.saveByRecord(actor);

        // then
        assertThat(actor.getActorId()).isNull();
        assertThat(newActorRecord.getActorId()).isNotNull();
    }

    @Test
    @DisplayName("SQL 실행 후 PK만 반환")
    @Transactional
    void insert_with_returning_pk() {

        // given
        var actor = new Actor();
        actor.setFirstName("John");
        actor.setLastName("Doe");
        actor.setLastUpdate(LocalDateTime.now());

        // when
        Long pk = actorRepository.saveWithReturningPkOnly(actor);

        // then
        assertThat(pk).isNotNull();
    }

    @Test
    @DisplayName("SQL 실행 후 해당 ROW 전체 반환")
    @Transactional
    void insert_with_returning() {

        // given
        var actor = new Actor();
        actor.setFirstName("John");
        actor.setLastName("Doe");
        actor.setLastUpdate(LocalDateTime.now());

        // when
        Actor pk = actorRepository.saveWithReturning(actor);

        // then
        assertThat(pk).hasNoNullFieldsOrProperties();
    }

    /**
     * insert into `actor` (`first_name`, `last_name`) values (?, ?), (?, ?), (?, ?), (?, ?), (?, ?)
     */
    @Test
    @DisplayName("bulk insert 예제")
    @Transactional
    void bulk_insert() {
        // given
        Actor actor1 = new Actor();
        actor1.setFirstName("John");
        actor1.setLastName("Doe");

        Actor actor2 = new Actor();
        actor2.setFirstName("John 2");
        actor2.setLastName("Doe 2");

        List<Actor> actorList = List.of(actor1, actor2);

        // when
        actorRepository.bulkInsertWithRows(actorList);
    }
}
