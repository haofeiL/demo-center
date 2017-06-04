package com.liang.demo.logback.appender.kafka.Controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by haofeiL on 2017/6/3.
 */
@Controller
public class KafkaAppenderController {
    private final Logger logger = LoggerFactory.getLogger(KafkaAppenderController.class);

    @RequestMapping("/kafkaAppender/hello")
    public void hello(int size) {
        for (int i = 0; i < size; i++) {
            logger.info(">>>>>>>>>" + i + "<<<<<<<<<<");
        }
    }
}
