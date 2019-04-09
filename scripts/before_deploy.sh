#!/usr/bin/env bash
set -ev

if  [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ]
then
	git config --local user.name $NAME
    git config --local user.email $EMAIL
    VERSION=$(mvn help:evaluate -Dexpression=project.version -q -DforceStdout)
    git tag ${VERSION}
fi
