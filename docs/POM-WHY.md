## Why coverage profile
This profile exists in order to avoid Jacoco running all the time during test, as I use it for code coverage analysis.

## Why release profile
As, in local or non-master build, a snapshot version is produced during packaging, release profile
is added to ensure that rule `</requireReleaseVersion>` of maven-enforcer-plugin does not run for non-master build.

## Why maven-enforcer-plugin
I believe it's good practice enforcing not only java and maven version but also no snapshot dependencies.

## Why maven-flatten-plugin
To allow version to be injected during the build this will work well with [semantic release](https://github.com/semantic-release/semantic-release) approach.

## Why maven-surefire-plugin
To execute unit tests and generate reports.