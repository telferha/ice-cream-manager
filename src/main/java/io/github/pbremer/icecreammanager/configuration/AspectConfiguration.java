package io.github.pbremer.icecreammanager.configuration;

import org.apache.commons.lang3.StringUtils;
import org.aspectj.lang.annotation.AfterReturning;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.annotation.EnableAspectJAutoProxy;

@EnableAspectJAutoProxy
public class AspectConfiguration {

    private static final Logger log =
            LoggerFactory.getLogger(AspectConfiguration.class);

    @AfterReturning(pointcut = "execution(*get*())", returning = "returnString")
    public String safeNullString(String returnString) {
	log.debug("Trimming string to empty on: {}", returnString);
	return StringUtils.trimToEmpty(returnString);
    }
}
