To show there is an issue of Eclipselink 2.7.1 + Kotlin. But the issue is fixed in Eclipselink 2.7.2

You could try to change the version of eclipselink in the [build.gradle.kts](build.gradle.kts) 2.7.1 and run the test, so that you will see
>org.eclipse.persistence.exceptions.PersistenceUnitLoadingException at EclipselinkFeature.kt:18
> Caused by: javax.persistence.PersistenceException at EclipselinkFeature.kt:18
> Caused by: org.eclipse.persistence.exceptions.EntityManagerSetupException at EclipselinkFeature.kt:18
> Caused by: java.lang.StackOverflowError


But when run the test with Eclipselink in 2.7.2+, ALL the tests will be passed

##### Reproduce steps
1. run `./gradlew clean test`
2. you will see the tests are fail
3. change Eclipselink version to 2.7.2+ (e.g: 2.7.2) in [build.gradle.kts](build.gradle.kts)
4. run `./gradlew clean test` again
5. ALL tests are passed

[build.gradle.kts]: build.gradle.kts