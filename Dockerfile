FROM java:8-jdk

VOLUME ["/opt/"]

ENV PATH $PATH:/opt/app
WORKDIR /opt/app

# To see work around for bug, uncomment this line...
RUN bash -c '([[ ! -d $JAVA_SECURITY_DIR ]] && ln -s $JAVA_HOME/lib $JAVA_HOME/conf) || (echo "Found java conf dir, package has been fixed, remove this hack"; exit -1)'


EXPOSE 8080

ENTRYPOINT ["gradlew"]