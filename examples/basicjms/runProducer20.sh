export JAVA_PROGRAM_ARGS=`echo "$@"`
mvn exec:java -Dexec.mainClass="ibm.gse.eda.basicjms20.Publisher20" -Dexec.args="$JAVA_PROGRAM_ARGS"
