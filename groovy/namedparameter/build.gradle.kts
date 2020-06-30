plugins {
    groovy

    application
}

repositories {
    jcenter()
}

dependencies {
    implementation("org.codehaus.groovy:groovy-all:2.5.10")

    testImplementation("org.spockframework:spock-core:1.3-groovy-2.5")
}

application {
    mainClassName = "dk.emstar.pocs.groovy.namedparameter.App"
}
