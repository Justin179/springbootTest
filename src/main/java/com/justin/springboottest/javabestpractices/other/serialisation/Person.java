package com.justin.springboottest.javabestpractices.other.serialisation;

import java.io.Serializable;
import java.util.List;


record Person(String name, int age, List<String> nicknames) implements Serializable {
}