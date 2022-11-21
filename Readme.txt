To Test this application, all the asked fonctionalities are tested on BankTransactionTests.class

As improvement would be interesting to try to test the Services more than the repositories, since the repositories are
just meant to be accessed by services, which work as intermediates with the repositories and the database itself to
avoid integrity problems.

Another improvement would be to add a server, and some endpoints to access the database through them.

Finally, a graphic user interface would be also interesting, which may be done with angular on frontEnd, doing the
calls to our endpoints on the backend to access the database and thus having a Model View Controller approach.

This improvements have not been added because of time limitations.