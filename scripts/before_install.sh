#!/usr/bin/env bash
if  [ "$TRAVIS_SECURE_ENV_VARS" = "true" ] && [ "$TRAVIS_PULL_REQUEST" = "false" ] && [ "$TRAVIS_BRANCH" = "master" ];then
    echo $GPG_SECRET_KEYS | base64 --decode | $GPG_EXECUTABLE --import
    echo $GPG_OWNERTRUST | base64 --decode | $GPG_EXECUTABLE --import-ownertrust
fi

