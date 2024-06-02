package org.woozi.pratice.jooq.actor;

import org.jooq.generated.tables.pojos.Actor;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.Collections;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class ActorRepositoryTest {

        @Autowired
        private ActorRepository actorRepository;

        @Test
        @DisplayName("and 조건 검색 - fistName와 LastName이 일치하는 배우 조회")
        void AND조건_1() {
            // given
            String firstName = "ED";
            String lastName = "CHASE";

            // when
            List<Actor> actorList = actorRepository.findByFirstnameAndLastName(firstName, lastName);

            // then
            assertThat(actorList).hasSize(1);
        }

        @Test
        @DisplayName("or 조건 검색 - fistName 또는 LastName이 일치하는 배우 조회")
        void or조건_1() {
            // given
            String firstName = "ED";
            String lastName = "CHASE";

            // when
            List<Actor> actorList = actorRepository.findByFirstnameOrLastName(firstName, lastName);

            // then
            assertThat(actorList).hasSizeGreaterThan(1);
        }

        @Test
        @DisplayName("in절 - 동적 조건 검색")
        void in절_동적_조건검색_1() {
            // when
            List<Actor> actorList = actorRepository.findByActorIdIn(List.of(1L));

            // then
            assertThat(actorList).hasSize(1);
        }

        @Test
        @DisplayName("in절 - 동적 조건 검색 - empty list시 제외")
        void in절_동적_조건검색_2() {
            // when
            List<Actor> actorList = actorRepository.findByActorIdIn(Collections.emptyList());

            // then
            assertThat(actorList).hasSizeGreaterThan(1);
        }

        @Test
        @DisplayName("다중 조건 검색 - 배우 이름으로 조회")
        void 다중_조건_검색_1() {
            // given
            var searchOption = new ActorFilmographySearchOption("LOLLOBRIGIDA", null);
            // when
            List<ActorFilmography> actorFilmographies = actorRepository.findActorFilmography(searchOption);

            // then
            assertThat(actorFilmographies).hasSize(1);
        }

        @Test
        @DisplayName("다중 조건 검색 - 배우 이름과 영화 제목으로 조회")
        void 다중_조건_검색_2() {
            // given
            var searchOption = new ActorFilmographySearchOption("LOLLOBRIGIDA", "COMMANDMENTS EXPRESS");

            // when
            List<ActorFilmography> actorFilmographies = actorRepository.findActorFilmography(searchOption);

            // then
            assertThat(actorFilmographies).hasSize(1);
            assertThat(actorFilmographies.get(0).getFilmList()).hasSize(1);
        }
    }