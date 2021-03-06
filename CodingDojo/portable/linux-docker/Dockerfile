FROM jetty:9.3 as javaworkspace
MAINTAINER Alexander Baglay <apofig@gmail.com>

USER root
ENV DEBIAN_FRONTEND noninteractive

ENV MAVEN_VERSION 3.5.4
ENV MAVEN_BASE_URL https://apache.osuosl.org/maven/maven-3/${MAVEN_VERSION}/binaries
ENV MAVEN_SHA ce50b1c91364cb77efe3776f756a6d92b76d9038b0a0782f7d53acf1e997a14d
ENV MAVEN_HOME /usr/share/maven
ENV M2_HOME /usr/share/maven

RUN apt-get update \
 && apt-get -y install -y openjdk-8-jdk \
 && apt-get -y install -y git \
 && mkdir -p /usr/share/maven /usr/share/maven/ref \
 && curl -fsSL -o /tmp/apache-maven.tar.gz ${MAVEN_BASE_URL}/apache-maven-${MAVEN_VERSION}-bin.tar.gz \
 && echo "${MAVEN_SHA} /tmp/apache-maven.tar.gz" | sha256sum -c - \
 && tar -xzf /tmp/apache-maven.tar.gz -C /usr/share/maven --strip-components=1 \
 && rm -f /tmp/apache-maven.tar.gz \
 && ln -s /usr/share/maven/bin/mvn /usr/bin/mvn

# -----------------------------------------------------------------------

FROM javaworkspace as codenjoysource
MAINTAINER Alexander Baglay <apofig@gmail.com>

ENV DEBIAN_FRONTEND noninteractive

ARG GIT_REPO=https://github.com/IzhevskCodeBattle/codebattle-server.git
ARG SKIP_TESTS=true
ARG REVISION

RUN cd /tmp \
 && git clone ${GIT_REPO} \
 && cd /tmp/codebattle-server/CodingDojo \
 && if [ "x$REVISION" = "x" ] ; \
         then \
             git checkout master ; \
         else \
             git checkout ${REVISION} ; \
         fi \
 && mvn clean install -DskipTests=${SKIP_TESTS}

# -----------------------------------------------------------------------

FROM codenjoysource as codenjoydeploy
MAINTAINER Alexander Baglay <apofig@gmail.com>

ENV DEBIAN_FRONTEND noninteractive

ARG GAMES=bomberman
ARG CONTEXT=codenjoy-contest

RUN cd /tmp/codebattle-server/CodingDojo/builder \
 && if [ "x$GAMES" = "x" ] ; \
         then \
             mvn clean install -Dcontext=${CONTEXT} -DallGames ; \
         else \
             mvn clean install -Dcontext=${CONTEXT} -P${GAMES} ; \
         fi \
 && cp /tmp/codebattle-server/CodingDojo/builder/target/${CONTEXT}.war /var/lib/jetty/webapps

VOLUME ["/var/lib/jetty/database"]

EXPOSE 8080

# -----------------------------------------------------------------------