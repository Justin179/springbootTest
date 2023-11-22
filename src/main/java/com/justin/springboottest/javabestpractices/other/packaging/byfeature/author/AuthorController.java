package com.justin.springboottest.javabestpractices.other.packaging.byfeature.author;

class AuthorController {

    private final AuthorService service;

    public AuthorController(AuthorService service) {
        this.service = service;
    }

    // implementation skipped for brevity
}
