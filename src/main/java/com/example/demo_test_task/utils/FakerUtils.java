package com.example.demo_test_task.utils;

import com.github.javafaker.Faker;

import java.util.Locale;

public final class FakerUtils {

    public static final Faker faker = new Faker();

    public static String phoneUA() {
        return "+380" + faker.phoneNumber().subscriberNumber(9);
    }

    public static String email() {
        return faker.internet().emailAddress();
    }

    public static String comment() {
        return faker.lorem().paragraph();
    }

    public static String requestId() {
        return faker.internet().uuid();
    }

    public static String fakeOtp() {return faker.regexify("[0-9][0-9]-[0-9][0-9]-[0-9][0-9]");}

    public static String fakePin() {return faker.numerify("########");}

    public static String fake5int() {return faker.numerify("#####");}

    public static String fakeUri() {return faker.bothify("????????&&.fakeurl.com");}

    public static String randomInt() { return String.valueOf(faker.random().nextInt(1000000)); }

    public static String password() { return faker.numerify("########");}
}
