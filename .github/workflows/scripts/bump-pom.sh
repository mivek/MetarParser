#!/usr/bin/env bash

if [[ $GIT_COMMIT_MESSAGE_SUBJECT =~ (Merge pull request.*feature) ]];then
    mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.nextMinorVersion}.0 -DprocessAllModules versions:commit
elif [[ $GIT_COMMIT_MESSAGE_SUBJECT =~ (Merge pull request.*major) ]];then
    mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.nextMajorVersion}.0.0 -DprocessAllModules versions:commit
else
    mvn build-helper:parse-version versions:set -DnewVersion=\${parsedVersion.majorVersion}.\${parsedVersion.minorVersion}.\${parsedVersion.nextIncrementalVersion} -DprocessAllModules versions:commit
fi
