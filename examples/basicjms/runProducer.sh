export JAVA_PROGRAM_ARGS=`echo "$@"`
mvn exec:java -Dexec.mainClass="ibm.gse.eda.basicjms.Publisher" -Dexec.args="$JAVA_PROGRAM_ARGS"
