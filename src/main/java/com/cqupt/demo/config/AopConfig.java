package com.cqupt.demo.config;

import com.cqupt.demo.Bean.Room;
import com.cqupt.demo.Dao.RoomDao;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.reflect.CodeSignature;
import org.springframework.stereotype.Component;

import javax.annotation.Resource;
import java.util.HashMap;
import java.util.Map;

@Component
@Aspect
public class AopConfig {

    @Resource
    private RoomDao roomDao;

    @Around(value = "execution(* com.cqupt.demo.Service.RoomService.queryPri(..))")
    public Object beforeAdvice(ProceedingJoinPoint joinPoint) {
//        System.out.println("前置通知...."+joinPoint);
        Map<String, Object> param = new HashMap<String, Object>();
        Object[] paramValues = joinPoint.getArgs();
        String[] paramNames = ((CodeSignature) joinPoint.getSignature()).getParameterNames();
        for (int i = 0; i < paramNames.length; i++) {
            param.put(paramNames[i], paramValues[i]);
        }
        Integer roomId = (Integer) param.get("movieId");
        String password = (String) param.get("password");
        Room room = null;
        try {
             room= roomDao.queryPri(roomId, password);
            joinPoint.proceed();
        } catch (Throwable throwable) {
            throwable.printStackTrace();
            return null;
        }

        return room;

    }


}
