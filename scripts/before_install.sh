#!/usr/bin/env bash
isPRMerged=false

if  [[ "$TRAVIS_SECURE_ENV_VARS" = "true" ]] && [[ "$TRAVIS_PULL_REQUEST" = "false" ]] && [[ "$TRAVIS_BRANCH" = "master" ]];then
    echo $GPG_SECRET_KEYS | base64 --decode | $GPG_EXECUTABLE --import
    echo $GPG_OWNERTRUST | base64 --decode | $GPG_EXECUTABLE --import-ownertrust
fi

if [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Merge pull request.*fix) ]] || [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Merge pull request.*clean) ]]; then
    mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} versions:commit
    isPRMerged=true
elif [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Merge pull request.*feature) ]] ; then
    mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.nextMinorVersion}.0 versions:commit
    isPRMerged=true
elif [[ "$TRAVIS_COMMIT_MESSAGE" =~ (Merge pull request.*major) ]] ; then
    mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.nextMajorVersion}.0.0 versions:commit
    isPRMerged=true
fi

if [[ "$isPRMerged" = true ]] ; then
    git config --global user.email "travis@travis-ci.org"
    git config --global user.name "Travis CI"
    git add pom.xml
    git commit -m "Bump pom"
    git push https://${TRAVIS_GIT_USER}@github.com/mivek/metarParser.git HEAD:master
fi
