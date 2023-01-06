@ECHO OFF
cd .
START java -Dserver.port=7003 -classpath ./build/install/mp01/lib/* com.distribuida.Servidor
START java -Dserver.port=7004 -classpath ./build/install/mp01/lib/* com.distribuida.Servidor
START java -Dserver.port=7005 -classpath ./build/install/mp01/lib/* com.distribuida.Servidor
START java -Dserver.port=7006 -classpath ./build/install/mp01/lib/* com.distribuida.Servidor