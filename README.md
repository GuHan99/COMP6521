# COMP 6521

***Announcement: So far the code shown here is an unoptimizable version !!!***

## Development

### Idea

Now, just for finish the mission. The idea is super easy, just divide the source file into several parts, each time we sort that subparts and output them to a tmp file. After sorting all subparts, we merge them into a new file and delete the tmp files.

**Note**: if you want to run the code, you need to change the `PROJECT_ROOT` variable in `Const.class`, give it the paht in your own machine. After that, you can check the directory `/src/test/java/` and run the code in `Test1.java`.

## Test 

### Test File

There are two test files for writing the code:

- [t1.txt](NonRepeatedRadomNumGenerator/t1.txt) contains 5,000 items;
- [t2.txt](NonRepeatedRadomNumGenerator/t2.txt) contains 10,000 items;

All 7-digit numbers in each file are distinct, but they may duplicate in two files.
Of course, you can generate more items if you want, please look at the `index.js` file under directory `NonRepeatedRadomNumGenerator`. If you want to generate more test files, `node.js` environment is required.

### Test Framework

The framework I used for testing is JUnit. It can be integrated easily into Java project by using Maven or Gradle:

```
// for Maven user, add following line in pom.xml
<dependency>
  <groupId>junit</groupId>
  <artifactId>junit</artifactId>
  <version>4.12</version>
  <scope>test</scope>
</dependency>

// for Gradle user, add following line in build.gradle
apply plugin: 'java'
dependencies {
  testCompile 'junit:junit:4.12'
}
```

Also you can just download the following two .jar files and add them into your test class path:

- junit.jar
- hamcrest-core.jar
