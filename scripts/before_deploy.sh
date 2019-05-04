#!/usr/bin/env bash
set -ev

if  [ "$TRAVIS_BRANCH" = "master" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ]
then
	git config --local user.name $NAME
    git config --local user.email $EMAIL
    git tag $1
fi
