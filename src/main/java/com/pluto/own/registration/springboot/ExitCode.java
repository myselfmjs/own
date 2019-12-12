package com.pluto.own.registration.springboot;

import org.springframework.boot.ExitCodeGenerator;
import org.springframework.stereotype.Component;

/**
 * @author ：pluto
 * @date ：Created in 2019/12/11 15:14
 */
@Component
public class ExitCode implements ExitCodeGenerator {
    @Override
    public int getExitCode() {
        System.out.println("-----------Application退出");
        return 0;
    }
}
