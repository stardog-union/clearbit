# Clearbit Java API

A basic Java implementation of the [Clearbit API](https://clearbit.com/docs).

Current version is 0.1 on 2015-03-31.  Using [git flow](http://nvie.com/posts/a-successful-git-branching-model/)
for development.

Sorry, there is not much in the way of tests.  The current code was thrown together during a hack session as part of a
separate project and was tested indirectly there.  But to run the few that are there you need to provide an API key
to use:

```bash
$ gradle test -Dclearbit.key=abc123
```