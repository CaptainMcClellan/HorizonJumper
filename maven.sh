mvn clean package -Dmaven.compiler.source=1.7 -Dmaven.compiler.target=1.7 -Dfile.encoding=UTF-8 -Dproject.build.sourceEncoding=UTF-8  | cat > errorz.txt; cat errorz.txt | less;
