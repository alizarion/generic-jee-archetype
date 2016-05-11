#!/bin/bash
# Usage: execute.sh [WildFly mode] [configuration file]
#
mvn clean install

cd wildfly-package

mvn wildfly:run -Dwildfly.version=8.2.0.Final
