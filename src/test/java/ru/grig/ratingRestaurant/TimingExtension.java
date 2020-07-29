package ru.grig.ratingRestaurant;

import org.junit.jupiter.api.Test;

//import org.junit.rules.ExternalResource;
//import org.junit.rules.Stopwatch;
//import org.junit.runner.Description;
import org.junit.jupiter.api.extension.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.util.StopWatch;

import java.util.concurrent.TimeUnit;

public class TimingExtension implements
        BeforeTestExecutionCallback, AfterTestExecutionCallback, BeforeAllCallback, AfterAllCallback {

    private static final Logger log = LoggerFactory.getLogger("result");

    private StopWatch stopWatch;

    @Override
    public void beforeAll(ExtensionContext extensionContext) throws Exception {
        stopWatch = new StopWatch("Execution time of " + extensionContext.getRequiredTestClass().getSimpleName());
    }

    @Override
    public void beforeTestExecution(ExtensionContext extensionContext) throws Exception {
        String testName = extensionContext.getDisplayName();
        log.info("\nStart " + testName);
        stopWatch.start(testName);
    }

    @Override
    public void afterTestExecution(ExtensionContext extensionContext) throws Exception {
        stopWatch.stop();
    }

    @Override
    public void afterAll(ExtensionContext extensionContext) throws Exception {
        log.info('\n' + stopWatch.prettyPrint() + '\n');
    }


//    private static final StringBuilder results = new StringBuilder();
//
//    // http://stackoverflow.com/questions/14892125/what-is-the-best-practice-to-determine-the-execution-time-of-the-bussiness-relev
//    public static final Stopwatch STOPWATCH = new Stopwatch() {
//        @Override
//        protected void finished(long nanos, Description description) {
//            String result = String.format("%-95s %7d", description.getDisplayName(), TimeUnit.NANOSECONDS.toMillis(nanos));
//            results.append(result).append('\n');
//            log.info(result + " ms\n");
//        }
//    };
//
//    //    https://dzone.com/articles/applying-new-jdk-11-string-methods
//    private static final String DELIM = "-".repeat(103);
//
//    public static final ExternalResource SUMMARY = new ExternalResource() {
//        @Override
//        protected void before() throws Throwable {
//            results.setLength(0);
//        }
//
//        @Override
//        protected void after() {
//            log.info("\n" + DELIM +
//                    "\nTest                                                                                       Duration, ms" +
//                    "\n" + DELIM + "\n" + results + DELIM + "\n");
//        }
//    };

}
