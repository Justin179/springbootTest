package com.justin.springboottest.javabestpractices.other.packaging.byfeature.author;

import java.time.LocalDate;

record Author(String id, String firstName, String lastName, LocalDate birthday) {
}
