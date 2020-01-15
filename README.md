# cft_sort_file_content
This project is a code example for Center of Financial Technologies

Task is described in spec/test task.docx file.

# Build
```
mvn clean package
```

# Run
Application requires following parameters (all parameters are required)
```
usage: gnu
    --content-type <arg>   Type of content (i-integer or s-string)
    --out-prefix <arg>     Prefix for output files
    --path <arg>           Absolute or relative path to directory
    --sort-mode <arg>      Sort mode (a-ascending or d-descending)
```


How to run example:
```
java -jar target\cft-sort-file-content-1.0.0-jar-with-dependencies.jar --path=cft_test_examples\example3 --out-prefix=sorted_ --content-type=i --sort-mode=d
```

# Result
Application saves sorted files into out directory as %path%/out in files with predefined prefix and same names.