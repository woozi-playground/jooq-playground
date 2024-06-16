package org.woozi.pratice.jooq.actor;

public class ActorUpdateRequest {
    private String firstName;
    private String lastName;


    public ActorUpdateRequest(final String firstName) {
        this.firstName = firstName;
    }

    public ActorUpdateRequest(final String firstName, final String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }
}
