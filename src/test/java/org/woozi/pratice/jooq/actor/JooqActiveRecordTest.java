package org.woozi.pratice.jooq.actor;

import org.jooq.DSLContext;
import org.jooq.generated.tables.ActorEntity;
import org.jooq.generated.tables.records.ActorRecord;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class JooqActiveRecordTest {

    @Autowired
    private ActorRepository actorRepository;

    @Autowired
    private DSLContext dslContext;

    @Test
    @DisplayName("SELECT 절 예제")
    void activeRecord_조회_예제() {
        // given
        Long actorId = 1L;

        // when
        ActorRecord actor = actorRepository.findRecordByActorId(actorId);

        // then
        assertThat(actor).hasNoNullFieldsOrProperties();
    }

    @Test
    @DisplayName("activeRecord refresh 예제")
    void activeRecord_refresh_예제() {
        // given
        Long actorId = 1L;
        ActorRecord actor = actorRepository.findRecordByActorId(actorId);
        actor.setFirstName(null);

        // when
        actor.refresh();

        // then
        assertThat(actor.getFirstName()).isNotBlank();
    }

    @Test
    @DisplayName("activeRecord store 예제 - insert")
    @Transactional
    void activeRecord_insert_예제() {
        // given
        ActorRecord actorRecord = dslContext.newRecord(ActorEntity.ACTOR);

        // when
        actorRecord.setFirstName("john");
        actorRecord.setLastName("doe");
        actorRecord.store();

        // 혹은
        // actor.insert();

        // then
        assertThat(actorRecord.getLastUpdate()).isNull();
    }

    @Test
    @DisplayName("activeRecord store 예제 - update")
    @Transactional
    void activeRecord_update_예제() {
        // given
        Long actorId = 1L;
        String newName = "test";
        ActorRecord actor = actorRepository.findRecordByActorId(actorId);

        // when
        actor.setFirstName(newName);
        actor.store();

        // 혹은
        // actor.update();

        // then
        assertThat(actor.getFirstName()).isEqualTo(newName);
    }

    @Test
    @DisplayName("activeRecord delete 예제")
    @Transactional
    void activeRecord_delete_예제() {
        // given
        ActorRecord actorRecord = dslContext.newRecord(ActorEntity.ACTOR);

        // when
        actorRecord.setFirstName("john");
        actorRecord.setLastName("doe");
        actorRecord.store();

        // when
        actorRecord.delete();

        // then
        assertThat(actorRecord).hasNoNullFieldsOrProperties();
    }
}