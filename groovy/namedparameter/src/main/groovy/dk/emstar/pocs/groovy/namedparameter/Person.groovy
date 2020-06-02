package dk.emstar.pocs.groovy.namedparameter

import groovy.transform.NamedVariant
import groovy.transform.builder.Builder


@Builder()
class Person {

    String firstname

    String lastname

    Person() {
    }

    Person(String firstname, String lastname) {
        this.firstname = firstname
        this.lastname = lastname
    }

    @NamedVariant
    String calculate(String i1, String i2) {
        return i1 + i2
    }

    @NamedVariant
    static String calculate1(String i1, String i2) {
        return i1 + i2
    }

}
