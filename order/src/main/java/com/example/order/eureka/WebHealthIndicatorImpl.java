package com.example.order.eureka;

import org.springframework.boot.actuate.health.Health;
import org.springframework.boot.actuate.health.HealthIndicator;
import org.springframework.boot.actuate.health.Status;
import org.springframework.stereotype.Service;

import java.util.Objects;

@Service
public class WebHealthIndicatorImpl implements HealthIndicator {
    private String status = "OPEN";
    public void setStatus(String status){
        this.status=status;
    }
    public String getStatus(){
        return this.status.toString();
    }
    @Override
    public Health health() {
        if(Objects.equals(status, "OPEN")){
            return new Health.Builder(Status.UP).build();
        }
        return new Health.Builder(Status.DOWN).build();
    }
}
